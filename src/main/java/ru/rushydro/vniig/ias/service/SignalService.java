package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Signal;

@Service
public class SignalService {

    private final SignalRepository signalRepository;

    @Autowired
    public SignalService(SignalRepository signalRepository) {
        this.signalRepository = signalRepository;
    }

    public Signal findById(Integer id) {
        return signalRepository.findOne(id);
    }
}
