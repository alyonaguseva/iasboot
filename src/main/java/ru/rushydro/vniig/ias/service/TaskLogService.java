package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.TaskLogRepository;
import ru.rushydro.vniig.ias.dao.TaskLogTypeRepository;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;
import ru.rushydro.vniig.ias.dao.entity.enumeration.TaskLogTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazik on 14.05.2017.
 */
@Service
public class TaskLogService {

    @Autowired
    TaskLogRepository taskLogRepository;

    @Autowired
    TaskLogTypeRepository taskLogTypeRepository;

    public void addStatus(Iterable<Task> tasks, String systemName) {
        List<TaskLog> taskLogs = new ArrayList<>();
        tasks.forEach(task -> {
            TaskLog taskLog = new TaskLog();
            taskLog.setDate(LocalDateTime.now());
            taskLog.setSuccess(true);
            taskLog.setTask(task);
            taskLog.setType(taskLogTypeRepository.findBySystemname(systemName));

            taskLogs.add(taskLog);
        });

        taskLogRepository.save(taskLogs);
    }

    public Page<TaskLog> findAll(Pageable pageable) {
        return taskLogRepository.findAll(pageable);
    }

}
