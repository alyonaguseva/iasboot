package ru.rushydro.vniig.ias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.ias.dao.ExchangeRepository;
import ru.rushydro.vniig.ias.service.ParseFileService;
import ru.rushydro.vniig.ias.service.TaskService;

import java.util.logging.Logger;

/**
 * Created by yazik on 13.05.2017.
 */
@Component
public class ScheduledTasks {

    private final static Logger log = Logger.getLogger(ScheduledTasks.class.getName());

    private final
    ExchangeRepository exchangeRepository;

    private final
    TaskService taskService;

    private final
    ParseFileService parseFileService;

    @Autowired
    public ScheduledTasks(ExchangeRepository exchangeRepository,
                          TaskService taskService,
                          ParseFileService parseFileService) {
        this.exchangeRepository = exchangeRepository;
        this.taskService = taskService;
        this.parseFileService = parseFileService;
    }

    @Scheduled(fixedRateString = "${get.task.time}")
    public void getTasksFromExchange() {
        exchangeRepository.getTasks();
    }

    @Scheduled(fixedRateString = "${process.task.time}")
    public void processTask() {
        taskService.processTasks();
    }

    @Scheduled(fixedRateString = "${process.file.time}")
    public void processFile() {
        parseFileService.parseFile();
    }

}
