package ru.rushydro.vniig.ias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.ias.dao.ExchangeRepository;
import ru.rushydro.vniig.ias.service.ParseFileService;
import ru.rushydro.vniig.ias.service.TagService;
import ru.rushydro.vniig.ias.service.TaskService;

/**
 * Created by yazik on 13.05.2017.
 */
@Component
public class ScheduledTasks {

    private final
    ExchangeRepository exchangeRepository;

    private final
    TaskService taskService;

    private final
    ParseFileService parseFileService;

    private final
    TagService tagService;

    @Autowired
    public ScheduledTasks(ExchangeRepository exchangeRepository,
                          TaskService taskService,
                          ParseFileService parseFileService, TagService tagService) {
        this.exchangeRepository = exchangeRepository;
        this.taskService = taskService;
        this.parseFileService = parseFileService;
        this.tagService = tagService;
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

    @Scheduled(fixedRateString = "${process.tag.time}")
    public void processTag() {
        tagService.processTags();
    }

}
