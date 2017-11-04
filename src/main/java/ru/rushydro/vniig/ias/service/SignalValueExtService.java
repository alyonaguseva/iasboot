package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SignalValueExtRepository;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.util.List;

/**
 * Created by yazik on 04.11.2017.
 */
@Service
public class SignalValueExtService {

    private final SignalValueExtRepository signalValueExtRepository;

    @Autowired
    public SignalValueExtService(SignalValueExtRepository signalValueExtRepository) {
        this.signalValueExtRepository = signalValueExtRepository;
    }

    public void saveAll(List<SignalValueExt> signalValueExts) {
        signalValueExtRepository.save(signalValueExts);
    }

}
