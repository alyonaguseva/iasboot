package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        signalValueExtRepository.saveAll(signalValueExts);
    }

    public SignalValueExt findByIdSignal(Integer id, Integer calibrated) {
        Page<SignalValueExt> page = signalValueExtRepository.findByIdSignal(id, calibrated, new PageRequest(0,1));
        if (page.getContent() != null && page.getContent().size() > 0) {
            return page.getContent().get(0);
        }
        return null;
    }

    public SignalValueExt findByIdSignal(Integer id) {
        Page<SignalValueExt> page = signalValueExtRepository.findByIdSignal(id, new PageRequest(0,1));
        if (page.getContent() != null && page.getContent().size() > 0) {
            return page.getContent().get(0);
        }
        return null;
    }

}
