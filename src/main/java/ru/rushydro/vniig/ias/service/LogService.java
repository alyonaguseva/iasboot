package ru.rushydro.vniig.ias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.LogRepository;
import ru.rushydro.vniig.ias.dao.TaskLogRepository;
import ru.rushydro.vniig.ias.dao.TaskLogTypeRepository;
import ru.rushydro.vniig.ias.dao.entity.Log;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazik on 14.05.2017.
 */
@Service
public class LogService {

    private final static Logger log = LoggerFactory.getLogger(LogService.class);

    @Autowired
    LogRepository logRepository;

    public Page<Log> findAll(Pageable pageable) {
        return logRepository.findByOrderByIdDesc(pageable);
    }

    public Page<Log> findAll(Specification<Log> specification, Pageable pageable) {
        return logRepository.findAll(specification, pageable);
    }

    public Log info(String message) {
        return save(message, "info");
    }

    public Log warning(String message) {
        return save(message, "warning");
    }

    public Log error(String message) {
        return save(message, "error");
    }

    public Log save(String message, String type) {
        Log logEntry = new Log();
        logEntry.setMessage(message);
        logEntry.setType(type);
        logEntry.setDate(LocalDateTime.now());

        try {
            logEntry = logRepository.save(logEntry);
        } catch (Exception e) {
            log.error("Ошибка записи лога в БД", e);
        }

        return logEntry;
    }

}
