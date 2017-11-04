package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.AppDataRepository;
import ru.rushydro.vniig.ias.dao.entity.AppData;

/**
 * Created by yazik on 04.11.2017.
 */
@Service
public class AppDataService {

    private final AppDataRepository appDataRepository;

    @Autowired
    public AppDataService(AppDataRepository appDataRepository) {
        this.appDataRepository = appDataRepository;
    }

    public void save(AppData appData) {
        appDataRepository.save(appData);
    }

    public AppData findById(Integer id) {
        return appDataRepository.findOne(id);
    }

}
