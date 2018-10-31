package ru.rushydro.vniig.ias.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.entity.Sensor;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValue;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskLogTypeEnum;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskStatusEnum;
import ru.rushydro.vniig.ias.service.TaskLogService;
import ru.rushydro.vniig.ias.service.TaskStatusService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yazik on 13.05.2017.
 */
@Service
public class ExchangeRepository {
    private final static Logger log = LoggerFactory.getLogger(ExchangeRepository.class.getName());

    private final TaskRepository taskRepository;

    private final JdbcTemplate jdbcTemplate;

    private final SignalRepository signalRepository;

    private final SensorRepository sensorRepository;

    private final MeasuredParameterRepository measuredParameterRepository;

    private final TaskStatusService taskStatusService;

    private final TaskLogService taskLogService;

    private final SignalValueRepository signalValueRepository;

    @Autowired
    public ExchangeRepository(@Qualifier("exchangeDataSource") DataSource dataSource,
                              TaskRepository taskRepository,
                              SignalRepository signalRepository,
                              SensorRepository sensorRepository,
                              MeasuredParameterRepository measuredParameterRepository,
                              TaskStatusService taskStatusService, TaskLogService taskLogService,
                              SignalValueRepository signalValueRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.taskRepository = taskRepository;
        this.signalRepository = signalRepository;
        this.sensorRepository = sensorRepository;
        this.measuredParameterRepository = measuredParameterRepository;
        this.taskStatusService = taskStatusService;
        this.taskLogService = taskLogService;
        this.signalValueRepository = signalValueRepository;
    }
    
    public synchronized void getTasks() {

        List<Task> tasks = new ArrayList<>();

        try {
            jdbcTemplate.execute("LOCK TABLE schedule WRITE, sensors_ext as se WRITE");

            SqlRowSet rowSet = jdbcTemplate
                    .queryForRowSet("select schedule.datime, se.sensor, se.code, schedule.id, schedule.expdate " +
                            "from schedule " +
                            "join sensors_ext se " +
                            "on schedule.sensor = se.sensor and schedule.code = se.code " +
                            "order by schedule.datime desc, schedule.id desc");
            while(rowSet.next()) {
                LocalDateTime date = rowSet.getTimestamp(1).toLocalDateTime();
                int sensorId = rowSet.getInt(2);
                int measuredParameterId = rowSet.getInt(3);
                Sensor sensor = sensorRepository.findById(sensorId).orElse(null);
                if (sensor != null) {
                    Signal signal = signalRepository.findBySensorAndMeasuredParameter(sensor,
                            measuredParameterRepository.findById(measuredParameterId).orElse(null));
                    if (signal != null) {
                        log.debug("Обработка сигнала: " + signal.getId() + " код: "
                                + signal.getMeasuredParameter().getId() + " дата " + date);
                        if (taskRepository.findByDateAndSignal(date, signal) != null) {
                            break;
                        } else {
                            Task task = new Task();
                            task.setSignal(signal);
                            task.setIdIDS(rowSet.getLong(4));
                            task.setDateMax(rowSet.getTimestamp(5).toLocalDateTime());
                            task.setDate(date);
                            task.setStatus(taskStatusService.findBySystemname(TaskStatusEnum.NEW.name()));
                            tasks.add(task);
                        }
                    } else {
                        log.warn("Сигнал с id датчика: " + sensorId + " и с кодом измеряемого параметра: "
                                + measuredParameterId + " не найден!");
                    }
                } else {
                    log.warn("Датчик с id: " + sensorId + " не найден!");
                }
            }

            jdbcTemplate.execute("delete from schedule");

            jdbcTemplate.execute("UNLOCK TABLES");

            commit();
        } catch (DataAccessException e) {
            log.error("Ошибка получения заданий: ", e);
            jdbcTemplate.execute("UNLOCK TABLES");
            rollback();
        }

        if (!tasks.isEmpty()) {
            taskLogService.addStatus(taskRepository.saveAll(tasks), TaskLogTypeEnum.NEW.name());
        }
    }

    public synchronized void sendTasks(List<Task> tasks) {
        if (tasks != null && !tasks.isEmpty()) {

            try {
                jdbcTemplate.execute("LOCK TABLE buffer  WRITE");

                tasks.forEach(task -> {
                    SignalValue signalValue = signalValueRepository.findByTask(task);
                    Object[] args = new Object[2];
                    args[0] = signalValue.getSignal().getSensor().getId();
                    args[1] = signalValue.getSignal().getMeasuredParameter().getId();
                    log.debug("Отправка данных датчика: " + args[0]);
                    jdbcTemplate.update("insert into buffer(sensor, date, code, value, errcode, comment) " +
                                    "values(?,?,?,?,?,?)", args[0], signalValue.getTime(), args[1],
                            signalValue.getValue(), signalValue.getErrorCode(),
                            signalValue.getComment());

                    task.setStatus(taskStatusService.findBySystemname(TaskStatusEnum.COMPLETE.name()));
                    task.setComplete(true);

                    taskLogService.addStatus(taskRepository.saveAll(Collections.singleton(task)),
                            TaskLogTypeEnum.COMPLETE.name());

                });

                jdbcTemplate.execute("UNLOCK TABLES");

                commit();
            } catch (DataAccessException e) {
                log.error("Ошибка отправки заданий: ", e);
                jdbcTemplate.execute("UNLOCK TABLES");
                rollback();
            }
        }
    }

    public void commit() {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            if (connection != null && !connection.getAutoCommit()) {
                connection.commit();
            }
        } catch (SQLException e) {
            log.error("Ошибка сохранения данных", e);
            rollback();
        }
    }

    public void rollback() {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            log.error("Ошибка сохранения данных", e);
        }
    }
}
