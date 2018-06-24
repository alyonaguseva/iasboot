package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.*;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValue;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskStatusEnum;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис по обработке заданий
 * Created by yazik on 14.05.2017.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskStatusService taskStatusService;

    private final TaskLogService taskLogService;

    private final SignalValueRepository signalValueRepository;

    private final SignalValueExtService signalValueExtService;

    private final ExchangeRepository exchangeRepository;

    private final SignalService signalService;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       TaskStatusService taskStatusService, TaskLogService taskLogService,
                       SignalValueRepository signalValueRepository,
                       SignalValueExtService signalValueExtService, ExchangeRepository exchangeRepository,
                       SignalService signalService) {
        this.taskRepository = taskRepository;
        this.taskStatusService = taskStatusService;
        this.taskLogService = taskLogService;
        this.signalValueRepository = signalValueRepository;
        this.signalValueExtService = signalValueExtService;
        this.exchangeRepository = exchangeRepository;
        this.signalService = signalService;
    }

    public void processTasks() {

        Page<Task> taskPage = taskRepository.findByCompleteFalseOrderByIdDesc(new PageRequest(0, 10000));

        List<Task> tasks = taskPage.getContent();

        Integer newStatusId = taskStatusService
                .findBySystemname(TaskStatusEnum.NEW.name()).getId();
        Integer sendToSensorId = taskStatusService
                .findBySystemname(TaskStatusEnum.SENDTOSENSOR.name()).getId();

        LocalDateTime now = LocalDateTime.now();

        //Обработка на просроченные
        processOverdue(tasks.stream().filter(task -> task.getDateMax().isBefore(now))
                .collect(Collectors.toList()));

        processNewTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(newStatusId))
                .collect(Collectors.toList()));
        processSendToSensorTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(sendToSensorId))
                .collect(Collectors.toList()));
    }

    public void processSendTasks() {

        Page<Task> taskPage = taskRepository.findByCompleteFalseOrderByIdDesc(new PageRequest(0, 10000));

        List<Task> tasks = taskPage.getContent();

        Integer needToSendId = taskStatusService
                .findBySystemname(TaskStatusEnum.NEEDTOSEND.name()).getId();

        LocalDateTime now = LocalDateTime.now();

        //Обработка на просроченные
        processOverdue(tasks.stream().filter(task -> task.getDateMax().isBefore(now))
                .collect(Collectors.toList()));

        processNeedToSendTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(needToSendId))
                .collect(Collectors.toList()));
    }

    private void processNewTasks(List<Task> tasks) {
        addStatus(tasks, TaskStatusEnum.SENDTOSENSOR.name());
    }

    private void processOverdue(List<Task> tasks) {
        tasks.forEach(task -> task.setComplete(true));
        addStatus(tasks, TaskStatusEnum.OVERDUE.name());
    }

    private void processSendToSensorTasks(List<Task> tasks) {
        tasks.forEach(task -> {
            Signal signal = task.getSignal();
            if (signal != null) {
                SignalValueExt signalValueExt = signalValueExtService.findByIdSignal(signal.getId(),
                        signal.getMeasuredParameter().getDataType() == 0 ? 1 : 0);
                if (signalValueExt != null) {
                    if (signalValueRepository.findByTask(task) == null) {
                        SignalValue signalValue = new SignalValue();
                        signalValue.setTask(task);
                        signalValue.setValue(signalValueExt.getValue());
                        signalValue.setSignal(task.getSignal());
                        signalValue.setTime(signalValueExt.getValueTime());
                        signalValue.setErrorCode(0);
                        signalValueRepository.save(signalValue);
                    }
                    addStatus(Collections.singletonList(task), TaskStatusEnum.NEEDTOSEND.name());
                }
            }
        });
    }

    private void processNeedToSendTasks(List<Task> tasks) {
        exchangeRepository.sendTasks(tasks);
    }

    private void addStatus(List<Task> tasks, String systemName) {
        tasks.forEach(task -> task.setStatus(taskStatusService.findBySystemname(systemName)));
        taskLogService.addStatus(taskRepository.save(tasks), systemName);
    }


    public void complete(Task task) {
        task.setComplete(true);
        addStatus(Collections.singletonList(task), TaskStatusEnum.COMPLETE.name());
    }
}
