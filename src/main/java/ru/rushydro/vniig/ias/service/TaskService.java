package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.*;
import ru.rushydro.vniig.ias.dao.entity.SignalValue;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Сервис по обработке заданий
 * Created by yazik on 14.05.2017.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskStatusRepository taskStatusRepository;

    private final TaskLogService taskLogService;

    private final SignalValueRepository signalValueRepository;

    private final ExchangeRepository exchangeRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       TaskStatusRepository taskStatusRepository,
                       TaskLogService taskLogService,
                       SignalValueRepository signalValueRepository,
                       ExchangeRepository exchangeRepository) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskLogService = taskLogService;
        this.signalValueRepository = signalValueRepository;
        this.exchangeRepository = exchangeRepository;
    }

    public void processTasks() {

        List<Task> tasks = taskRepository.findByCompleteFalse();

        Integer newStatusId = taskStatusRepository
                .findBySystemname(TaskStatusEnum.NEW.name()).getId();
        Integer sendToSensorId = taskStatusRepository
                .findBySystemname(TaskStatusEnum.SENDTOSENSOR.name()).getId();
        Integer needToSendId = taskStatusRepository
                .findBySystemname(TaskStatusEnum.NEEDTOSEND.name()).getId();

        processNewTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(newStatusId))
                .collect(Collectors.toList()));
        processSendToSensorTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(sendToSensorId))
                .collect(Collectors.toList()));
        processNeedToSendTasks(tasks.stream().filter(task -> task.getStatus().getId()
                .equals(needToSendId))
                .collect(Collectors.toList()));
    }

    private void processNewTasks(List<Task> tasks) {

//        Random random = new Random();

        //todo send

        addStatus(tasks, TaskStatusEnum.SENDTOSENSOR.name());

//        List<SignalValue> signalValues = new ArrayList<>();
//        tasks.forEach(task -> {
//            SignalValue signalValue = new SignalValue();
//            signalValue.setTask(task);
//            signalValue.setSignal(task.getSignal());
//            signalValue.setTime(LocalDateTime.now());
//            signalValue.setValue(new BigDecimal(random.nextDouble() * 100));
//            signalValues.add(signalValue);
//        });
//
//        signalValueRepository.save(signalValues);

        //todo remove
    }

    private void processSendToSensorTasks(List<Task> tasks) {
        tasks.forEach(task -> {
            if (signalValueRepository.findByTask(task) != null) {
                addStatus(Collections.singletonList(task), TaskStatusEnum.NEEDTOSEND.name());
            }
        });
    }

    private void processNeedToSendTasks(List<Task> tasks) {
        exchangeRepository.sendTasks(tasks);
    }

    private void addStatus(List<Task> tasks, String systemName) {
        tasks.forEach(task -> task.setStatus(taskStatusRepository.findBySystemname(systemName)));
        taskLogService.addStatus(taskRepository.save(tasks), systemName);
    }


    public void complete(Task task) {
        task.setComplete(true);
        addStatus(Collections.singletonList(task), TaskStatusEnum.COMPLETE.name());
    }
}
