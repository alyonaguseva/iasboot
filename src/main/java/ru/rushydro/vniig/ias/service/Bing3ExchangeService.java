package ru.rushydro.vniig.ias.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.StringUtils;
import ru.rushydro.vniig.ias.dao.Bing3ExchangeRepository;
import ru.rushydro.vniig.ias.dao.ExchangeRepository;
import ru.rushydro.vniig.ias.dao.entity.Bing3Exchange;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.util.List;

@Service
public class Bing3ExchangeService {
    private final Logger log = LoggerFactory.getLogger(Bing3ExchangeService.class);

    private final SignalService signalService;

    private final SignalValueExtService signalValueExtService;

    private final Bing3ExchangeRepository bing3ExchangeRepository;

    public Bing3ExchangeService(SignalService signalService, SignalValueExtService signalValueExtService, ExchangeRepository exchangeRepository, Bing3ExchangeRepository bing3ExchangeRepository) {
        this.signalService = signalService;
        this.signalValueExtService = signalValueExtService;
        this.bing3ExchangeRepository = bing3ExchangeRepository;
    }

    public void updateData() {
        List<Signal> signals = signalService.getAll();
        signals.forEach(s -> {
            SignalValueExt signalValueExt = signalValueExtService.findByIdSignal(s.getId());
            if (signalValueExt != null) {
                Bing3Exchange exchange = new Bing3Exchange();
                exchange.setIdExternalSignal(StringUtils.isNotEmpty(s.getIdExternalSignal())
                                ? s.getIdExternalSignal() : s.getId().toString());

                exchange.setValue(signalValueExt.getValue());
                exchange.setValueTime(signalValueExt.getValueTime());

                bing3ExchangeRepository.save(exchange);
            } else {
                log.debug("Значение сигнала с id " + s.getId() + " не найдено!");
            }
        });

    }

}
