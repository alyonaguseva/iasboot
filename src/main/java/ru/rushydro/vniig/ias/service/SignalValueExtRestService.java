package ru.rushydro.vniig.ias.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesRequest;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesResponse;
import ru.rushydro.vniig.ias.webservice.SignalValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignalValueExtRestService {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(SignalValueExtRestService.class.getName());

    private final SignalRepository signalRepository;

    private final SignalValueExtService signalValueExtService;

    @Autowired
    public SignalValueExtRestService(SignalRepository signalRepository, SignalValueExtService signalValueExtService) {
        this.signalRepository = signalRepository;
        this.signalValueExtService = signalValueExtService;
    }


    public SendSignalValuesResponse sendSignalValues(SendSignalValuesRequest request) {
        SendSignalValuesResponse response = new SendSignalValuesResponse();
        List<SignalValueExt> values = new ArrayList<>();

        if (request != null && request.getSignalValues() != null) {
            List<Long> notFoundSignals = new ArrayList<>();

            for (SignalValue value : request.getSignalValues()) {
                Signal signal = signalRepository.findById((int) value.getSignalId()).orElse(null);
                if (signal != null) {
                    SignalValueExt signalValueExt = new SignalValueExt();
                    signalValueExt.setValue(new BigDecimal(value.getSignalValue()));
                    signalValueExt.setSignalId(signal.getId());
                    signalValueExt.setCalibrated(0);
                    signalValueExt.setValueTime(LocalDateTime.now());
                    values.add(signalValueExt);
                    log.info("Id полученного датчика: " + signalValueExt.getSignalId() +
                            " значение сигнала: " + signalValueExt.getValue());
                } else {
                    notFoundSignals.add(value.getSignalId());
                }
            }

            if (!notFoundSignals.isEmpty()) {
                response.setStatusCode(1);
                response.setStatusDescription(notFoundSignals.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")));
            }

            log.info("Сохранение данных датчиков в базу данных.");
            signalValueExtService.saveAll(values);
        }

        return response;
    }

}
