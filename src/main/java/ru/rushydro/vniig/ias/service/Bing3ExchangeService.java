package ru.rushydro.vniig.ias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.StringUtils;
import ru.rushydro.vniig.ias.dao.Bing3ExchangeRepository;
import ru.rushydro.vniig.ias.dao.entity.Bing3Exchange;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Bing3ExchangeService {
    private final Logger log = LoggerFactory.getLogger(Bing3ExchangeService.class);

    private final SignalService signalService;

    private final SignalValueExtService signalValueExtService;

    private final Bing3ExchangeRepository bing3ExchangeRepository;

    private final LogService logService;

    public Bing3ExchangeService(SignalService signalService, SignalValueExtService signalValueExtService,
                                Bing3ExchangeRepository bing3ExchangeRepository, LogService logService) {
        this.signalService = signalService;
        this.signalValueExtService = signalValueExtService;
        this.bing3ExchangeRepository = bing3ExchangeRepository;
        this.logService = logService;
    }

    public Bing3Exchange findByIdExternalSignal(String id) {
        Page<Bing3Exchange> page = bing3ExchangeRepository.findByIdExternalSignal(id, new PageRequest(0,1));
        if (page.getContent() != null && page.getContent().size() > 0) {
            return page.getContent().get(0);
        }
        return null;
    }

    public void updateData() {
        try {
            log.debug("Запуск задачи обмена данными с bing3");
            logService.info("Запуск задачи обмена данными с bing3");
            List<Signal> signals = signalService.getAll();
            logService.info("Для обмена получено " + signals.size() + " сигналов");
            AtomicInteger count = new AtomicInteger();
            signals.forEach(s -> {
                LocalDateTime localDateTime = null;

                Bing3Exchange oldExchange = findByIdExternalSignal(StringUtils.isNotEmpty(s.getIdExternalSignal())
                        ? s.getIdExternalSignal() : s.getId().toString());
                if (oldExchange != null) {
                    localDateTime = oldExchange.getValueTime();
                }

                SignalValueExt signalValueExt = localDateTime != null ? signalValueExtService.findByIdSignalAndValueTimeIsAfter(s.getId(), localDateTime)
                        : signalValueExtService.findByIdSignal(s.getId());
                if (signalValueExt != null) {
                    Bing3Exchange exchange = new Bing3Exchange();
                    exchange.setIdExternalSignal(StringUtils.isNotEmpty(s.getIdExternalSignal())
                            ? s.getIdExternalSignal() : s.getId().toString());

                    exchange.setValue(signalValueExt.getValue());
                    exchange.setValueTime(signalValueExt.getValueTime());

                    bing3ExchangeRepository.save(exchange);
                    count.getAndIncrement();
                } else {
                    String text = "Новые значение сигнала с id " + s.getId() + " не были получены и не будут выгружены в таблицу обмена";
                    log.debug(text);
                    logService.warning(text);
                }
            });
            logService.info("Задача обмена данными с bing3 выполнена. Загружено " + count.get() + " значений сигналов. Для "
                    + (signals.size() - count.get()) + " данные не загружены.");
        } catch (Exception e) {
            log.error("Ошибка взаимодействия с Bing3", e);
            logService.error("Ошибка взаимодействия с Bing3: " + e.getMessage());
        }


    }

}
