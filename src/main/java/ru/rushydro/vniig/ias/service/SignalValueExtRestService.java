package ru.rushydro.vniig.ias.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;
import ru.rushydro.vniig.ias.util.TimeUtil;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesRequest;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesResponse;
import ru.rushydro.vniig.ias.webservice.SignalValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignalValueExtRestService {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(SignalValueExtRestService.class.getName());

    private final SignalRepository signalRepository;

    private final SignalValueExtService signalValueExtService;

    private final LogService logService;

    @Autowired
    public SignalValueExtRestService(SignalRepository signalRepository, SignalValueExtService signalValueExtService, LogService logService) {
        this.signalRepository = signalRepository;
        this.signalValueExtService = signalValueExtService;
        this.logService = logService;
    }


    public SendSignalValuesResponse sendSignalValues(SendSignalValuesRequest request) {
        SendSignalValuesResponse response = new SendSignalValuesResponse();
        List<SignalValueExt> values = new ArrayList<>();

        logService.info("Получены значения сигналов с rest-сервиса");

        try {
            if (request != null && request.getSignalValue() != null) {
                for (SignalValue value : request.getSignalValue()) {
                    Signal signal = signalRepository.findById((int) value.getSignalId()).orElse(null);
                    if (signal != null) {
                        SignalValueExt signalValueExt = new SignalValueExt();
                        signalValueExt.setValue(new BigDecimal(value.getSignalValue()));
                        signalValueExt.setSignalId(signal.getId());
                        signalValueExt.setCalibrated(0);
                        signalValueExt.setValueTime(value.getSignalDateTime() != null
                                ? TimeUtil.convertToLocalDateTime(value.getSignalDateTime()) : LocalDateTime.now());
                        values.add(signalValueExt);
                        log.debug("Id полученного датчика: " + signalValueExt.getSignalId() +
                                " значение сигнала: " + signalValueExt.getValue());
                    } else {
                        response.setStatusCode(2);
                        response.setStatusDescription("Сигналы с указанным идентификатором не найдены");
                        response.getNotFoundSignalId().add(value.getSignalId());

                        logService.warning("Сигнал с указанным идентификатором: " + value.getSignalId() + " не найден");
                    }
                }

                log.info("Сохранение данных датчиков в базу данных.");
                signalValueExtService.saveAll(values);
                logService.info("Сохранение данных датчиков в базу данных");
            } else {
                response.setStatusCode(3);
                response.setStatusDescription("Значения сигналов не переданы");
                logService.warning("Значения сигналов не переданы");
            }
        } catch (Exception e) {
            logService.error("Ошибка сохранение данных сигналов: " + e.getMessage());
            log.error("Ошибка сохранение данных сигналов: ", e);
            response.setStatusCode(1);
            response.setStatusDescription("Ошибка сохранение значения сигналов: " + e.getMessage());
        }

        return response;
    }

}
