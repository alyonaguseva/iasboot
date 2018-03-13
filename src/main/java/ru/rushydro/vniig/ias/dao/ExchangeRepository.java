package ru.rushydro.vniig.ias.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.entity.Sensor;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValue;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskLogTypeEnum;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskStatusEnum;
import ru.rushydro.vniig.ias.service.ParseFileService;
import ru.rushydro.vniig.ias.service.TaskLogService;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yazik on 13.05.2017.
 */
@Service
public class ExchangeRepository {
    private final static Logger log = Logger.getLogger(ExchangeRepository.class.getName());

    private final TaskRepository taskRepository;

    private final JdbcTemplate jdbcTemplate;

    private final SignalRepository signalRepository;

    private final SensorRepository sensorRepository;

    private final MeasuredParameterRepository measuredParameterRepository;

    private final TaskStatusRepository taskStatusRepository;

    private final TaskLogService taskLogService;

    private final SignalValueRepository signalValueRepository;

    @Autowired
    public ExchangeRepository(@Qualifier("exchangeDataSource") DataSource dataSource,
                              TaskRepository taskRepository,
                              SignalRepository signalRepository,
                              SensorRepository sensorRepository,
                              MeasuredParameterRepository measuredParameterRepository,
                              TaskStatusRepository taskStatusRepository,
                              TaskLogService taskLogService,
                              SignalValueRepository signalValueRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.taskRepository = taskRepository;
        this.signalRepository = signalRepository;
        this.sensorRepository = sensorRepository;
        this.measuredParameterRepository = measuredParameterRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskLogService = taskLogService;
        this.signalValueRepository = signalValueRepository;
    }
    
    public synchronized void getTasks() {

        List<Task> tasks = new ArrayList<>();

        jdbcTemplate.execute("LOCK TABLE schedule as s WRITE, sensors_ext as se WRITE");

        SqlRowSet rowSet = jdbcTemplate
                .queryForRowSet("select s.datime, se.sensor, se.code, s.id, s.expdate " +
                        "from schedule s " +
                        "join sensors_ext se " +
                        "on s.sensor = se.sensor " +
                        "order by s.datime desc, s.id desc");
        while(rowSet.next()) {
            LocalDateTime date = rowSet.getTimestamp(1).toLocalDateTime();
            int sensorId = rowSet.getInt(2);
            int measuredParameterId = rowSet.getInt(3);
            Sensor sensor = sensorRepository.findOne(sensorId);
            if (sensor != null) {
                Signal signal = signalRepository.findBySensorAndMeasuredParameter(sensor,
                        measuredParameterRepository.findOne(measuredParameterId));
                if (signal != null) {
                    if (taskRepository.findByDateAndSignal(date, signal) != null) {
                        break;
                    } else {
                        Task task = new Task();
                        task.setSignal(signal);
                        task.setIdIDS(rowSet.getLong(4));
                        task.setDateMax(rowSet.getTimestamp(5).toLocalDateTime());
                        task.setDate(date);
                        task.setStatus(taskStatusRepository.findBySystemname(TaskStatusEnum.NEW.name()));
                        tasks.add(task);
                    }
                } else {
                    log.warning("Сигнал с id датчика: " + sensorId + " и с кодом измеряемого параметра: "
                            + measuredParameterId + " не найден!");
                }
            } else {
                log.warning("Датчик с id: " + sensorId + " не найден!");
            }
        }

        jdbcTemplate.execute("LOCK TABLE schedule WRITE");

        jdbcTemplate.execute("delete from schedule");

        jdbcTemplate.execute("UNLOCK TABLES");

        if (!tasks.isEmpty()) {
            taskLogService.addStatus(taskRepository.save(tasks), TaskLogTypeEnum.NEW.name());
        }
    }

    public synchronized void sendTasks(List<Task> tasks) {
        jdbcTemplate.execute("LOCK TABLE buffer  WRITE");

        tasks.forEach(task -> {
            SignalValue signalValue = signalValueRepository.findByTask(task);
            Object[] args = new Object[2];
            args[0] = signalValue.getSignal().getSensor().getId();
            args[1] = signalValue.getSignal().getMeasuredParameter().getId();
            jdbcTemplate.update("insert into buffer(sensor, date, code, value, errcode, comment) " +
                    "values(?,?,?,?,?,?)", args[0], signalValue.getTime(), args[1],
                                            signalValue.getValue(), signalValue.getErrorCode(),
                                            signalValue.getComment());

            task.setStatus(taskStatusRepository.findBySystemname(TaskStatusEnum.COMPLETE.name()));
            task.setComplete(true);

            taskLogService.addStatus(taskRepository.save(Collections.singleton(task)),
                    TaskLogTypeEnum.COMPLETE.name());

        });

        jdbcTemplate.execute("UNLOCK TABLES");
    }
}
