package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Sensor;
import ru.rushydro.vniig.ias.dao.entity.Signal;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignalService {

    private final SignalRepository signalRepository;

    @Autowired
    public SignalService(SignalRepository signalRepository) {
        this.signalRepository = signalRepository;
    }

    public Signal findById(Integer id) {
        return signalRepository.findById(id).orElse(null);
    }

    public List<Signal> findByInTag(Boolean inTag) {
        List<Signal> signals = new ArrayList<>();
        signalRepository.findByInTag(inTag).forEach(signals::add);
        return signals;
    }
}
