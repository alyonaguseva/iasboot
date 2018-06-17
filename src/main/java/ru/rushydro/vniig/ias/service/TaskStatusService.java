package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.TaskStatusRepository;
import ru.rushydro.vniig.ias.dao.entity.TaskStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    private Map<String, TaskStatus> statusMap = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public TaskStatus findBySystemname(String systemname) {
        return statusMap.computeIfAbsent(systemname, k -> taskStatusRepository.findBySystemname(systemname));
    }

}
