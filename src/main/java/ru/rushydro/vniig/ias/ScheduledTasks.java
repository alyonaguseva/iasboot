package ru.rushydro.vniig.ias;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
    private final static Logger log = LoggerFactory.getLogger(ScheduledTasks.class.getName());

    private final
    ExchangeRepository exchangeRepository;

    private final
    TaskService taskService;

    private final
    ParseFileService parseFileService;

    private final
    TagService tagService;

    @Value("${insert.test.data:false}")
    private boolean insertTestData;

    @Autowired
    public ScheduledTasks(ExchangeRepository exchangeRepository,
                          TaskService taskService,
                          ParseFileService parseFileService, TagService tagService) {
        this.exchangeRepository = exchangeRepository;
        this.taskService = taskService;
        this.parseFileService = parseFileService;
        this.tagService = tagService;
    }

    @Async
    @Scheduled(fixedRateString = "${get.task.time}")
    public void getTasksFromExchange() {
        log.debug("Запуск задачи получение заданий");
        exchangeRepository.getTasks();
    }

    @Async
    @Scheduled(fixedRateString = "${process.task.time}")
    public void processTask() {
        log.debug("Запуск задачи обработки заданий");
        taskService.processTasks();
    }

    @Async
    @Scheduled(fixedRateString = "${process.file.time}")
    public void processFile() {
        log.debug("Запуск задачи парсинга файла");
        parseFileService.parseFile();
    }

    @Async
    @Scheduled(fixedRateString = "${test.file.time:60000}")
    public void insertTestFileData() {
        if (insertTestData) {
            log.debug("Запуск задачи ввода тестовых данных файла");
            parseFileService.insertTestData();
        }
    }

    @Async
    @Scheduled(fixedRateString = "${process.tag.time}")
    public void processTag() {
        log.debug("Запуск задачи получения данных с json интерфейса");
        tagService.processTags();
    }

}
