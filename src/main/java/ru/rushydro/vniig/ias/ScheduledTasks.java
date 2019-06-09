package ru.rushydro.vniig.ias;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.ias.dao.ExchangeRepository;
import ru.rushydro.vniig.ias.service.Bing3ExchangeService;
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

    private final Bing3ExchangeService bing3ExchangeService;

    private boolean getTaskProgress;

    private boolean processTaskProgress;

    private boolean processSendTaskProgress;

    private Long time = 1000L;

    @Value("${work.exchange.repository:true}")
    private boolean workExchangeRepository;

    @Value("${insert.test.data:false}")
    private boolean insertTestData;

    @Value("${bing3.exchange:false}")
    private boolean bing3Exchange;

    @Autowired
    public ScheduledTasks(ExchangeRepository exchangeRepository,
                          TaskService taskService,
                          ParseFileService parseFileService, TagService tagService, Bing3ExchangeService bing3ExchangeService) {
        this.exchangeRepository = exchangeRepository;
        this.taskService = taskService;
        this.parseFileService = parseFileService;
        this.tagService = tagService;
        this.bing3ExchangeService = bing3ExchangeService;
    }

    @Async
    @Scheduled(fixedRateString = "${get.task.time}")
    public void getTasksFromExchange() {
        if (workExchangeRepository) {
            try {
                if (!getTaskProgress) {
                    getTaskProgress = true;
                    log.debug("Запуск задачи получение заданий");
                    exchangeRepository.getTasks();
                    getTaskProgress = false;
                }

            } catch (Exception e) {
                log.error("Ошибка получения заданий: ", e);
                getTaskProgress = false;
            }
        }
    }

    @Async
    @Scheduled(fixedRateString = "${process.task.time}")
    public void processTask() {
        try {
            if (!processTaskProgress) {
                processTaskProgress = true;
                log.debug("Запуск задачи обработки заданий");
                taskService.processTasks();
                processTaskProgress = false;
            }
        } catch (Exception e) {
            log.error("Ошибка обработки заданий: ", e);
            processTaskProgress = false;
        }
    }

    @Async
    @Scheduled(fixedRateString = "${process.task.time}")
    public void processSendTask() {
        try {
            if (!processSendTaskProgress) {
                processSendTaskProgress = true;
                log.debug("Запуск задачи отправки заданий");
                taskService.processSendTasks();
                processSendTaskProgress = false;
            }
        } catch (Exception e) {
            log.error("Ошибка отправки заданий: ", e);
            processSendTaskProgress = false;
        }
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
//            log.debug("Запуск задачи ввода тестовых данных файла");
//            parseFileService.insertTestData();
        }
    }

    @Async
    @Scheduled(fixedRateString = "${process.tag.time}")
    public void processTag() {
        log.debug("Запуск задачи получения данных с json интерфейса");
        tagService.processTags();
    }

//    @Async
//    @Scheduled(fixedRateString = "${bing3.exchange.time:60000}")
//    public void bing3Exchange() {
//        log.debug("Запуск задачи обмена данными с bing3");
//        if (bing3Exchange) {
//            bing3ExchangeService.updateData();
//        }
//    }
}
