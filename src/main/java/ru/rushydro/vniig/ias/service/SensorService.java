package ru.rushydro.vniig.ias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.SensorRepository;
import ru.rushydro.vniig.ias.dao.entity.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazik on 14.05.2017.
 */
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getAll() {
        List<Sensor> sensors = new ArrayList<>();
        sensorRepository.findAll().forEach(sensors::add);
        return sensors;
    }

    public List<Sensor> findByInTag(Boolean inTag) {
        List<Sensor> sensors = new ArrayList<>();
        sensorRepository.findByInTag(inTag).forEach(sensors::add);
        return sensors;
    }

}
