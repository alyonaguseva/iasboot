package ru.rushydro.vniig.ias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.ias.dao.ExchangeRepository;
import ru.rushydro.vniig.ias.dao.TaskRepository;
import ru.rushydro.vniig.ias.service.TaskService;

/**
 * Created by yazik on 13.05.2017.
 */
@Component
public class ScheduledTasks {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    TaskService taskService;

    @Scheduled(fixedRate = 60000)
    public void getTasksFromExchange() {
        exchangeRepository.getTasks();
    }

    @Scheduled(fixedRate = 30000)
    public void processTask() {
        taskService.processTasks();
    }

}
