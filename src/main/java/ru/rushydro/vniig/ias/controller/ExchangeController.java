package ru.rushydro.vniig.ias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rushydro.vniig.ias.dao.*;
import ru.rushydro.vniig.ias.dao.entity.MeasuredParameter;
import ru.rushydro.vniig.ias.types.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {
    
    private final MeasuredParameterRepository measuredParameterRepository;

    private final SensorRepository sensorRepository;

    private final SignalRepository signalRepository;

    private final SignalTypeRepository signalTypeRepository;

    private final ObjectMonitorRepository objectMonitorRepository;

    private final PL302Repository pl302Repository;

    @Autowired
    public ExchangeController(MeasuredParameterRepository measuredParameterRepository,
                              SensorRepository sensorRepository,
                              SignalRepository signalRepository,
                              SignalTypeRepository signalTypeRepository,
                              ObjectMonitorRepository objectMonitorRepository, PL302Repository pl302Repository) {
        this.measuredParameterRepository = measuredParameterRepository;
        this.sensorRepository = sensorRepository;
        this.signalRepository = signalRepository;
        this.signalTypeRepository = signalTypeRepository;
        this.objectMonitorRepository = objectMonitorRepository;
        this.pl302Repository = pl302Repository;
    }

    @RequestMapping("/measures")
    @ResponseBody
    public List<Measure> getMeasuresParams(){
        List<Measure> result = new ArrayList<>();
        measuredParameterRepository.findAll().forEach(entity -> {
           Measure measure = new Measure();
           measure.setId(entity.getId());
           measure.setName(entity.getName());
           measure.setDataType(entity.getDataType());
           result.add(measure);
       });
        result.sort(Comparator.comparingInt(Measure::getId));
        return result;
    }

    @RequestMapping("/combo/measures")
    @ResponseBody
    public List<Dictionary> getMeasuresParamsDictionary(){
        List<Dictionary> result = new ArrayList<>();
        measuredParameterRepository.findAll().forEach(entity -> {
            Dictionary measure = new Dictionary();
            measure.setId(entity.getId());
            measure.setName(entity.getName());
            result.add(measure);
        });
        result.sort(Comparator.comparingInt(Dictionary::getId));
        return result;
    }

    @RequestMapping("/measure/save")
    @ResponseBody
    public SimpleResponse saveMeasuresParam(@RequestBody Measure measure){
        MeasuredParameter measuredParameter = measuredParameterRepository.findById(measure.getId()).orElse(null);
        if (measuredParameter == null) {
            measuredParameter = new MeasuredParameter();
            measuredParameter.setId(measure.getId());
        }
        measuredParameter.setName(measure.getName());
        measuredParameter.setDataType(measure.getDataType());
        try {
            measuredParameterRepository.save(measuredParameter);
            return new SimpleResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse(false, e.getMessage());
        }
    }

    @RequestMapping("/sensors")
    @ResponseBody
    public List<Sensor> getSensors(){
        List<Sensor> result = new ArrayList<>();
        sensorRepository.findAll().forEach(entity -> {
            Sensor sensor = new Sensor();
            Dictionary objMon = new Dictionary();
            objMon.setId(entity.getObjectMonitor().getId());
            objMon.setName(entity.getObjectMonitor().getName());
            if (entity.getPl302() != null) {
                Dictionary pl302 = new Dictionary();
                pl302.setId(entity.getPl302().getId());
                pl302.setName(entity.getPl302().getName());
                sensor.setPl302(pl302);
            }
            sensor.setId(entity.getId());
            sensor.setName(entity.getName());
            sensor.setObjMonitor(objMon);
            sensor.setType(entity.getType());
            sensor.setTagName(entity.getTagName());
            result.add(sensor);
        });
        result.sort(Comparator.comparingInt(Sensor::getId));
        return result;
    }

    @RequestMapping("/combo/sensors")
    @ResponseBody
    public List<Dictionary> getSensorsDictionary(){
        List<Dictionary> result = new ArrayList<>();
        sensorRepository.findAll().forEach(entity -> {
            Dictionary sensor = new Dictionary();
            sensor.setId(entity.getId());
            sensor.setName(entity.getName());
            result.add(sensor);
        });
        result.sort(Comparator.comparingInt(Dictionary::getId));
        return result;
    }

    @RequestMapping("/combo/signalTypes")
    @ResponseBody
    public List<Dictionary> getSignalTypesDictionary(){
        List<Dictionary> result = new ArrayList<>();
        signalTypeRepository.findAll().forEach(entity -> {
            Dictionary sensor = new Dictionary();
            sensor.setId(entity.getId());
            sensor.setName(entity.getName());
            result.add(sensor);
        });
        result.sort(Comparator.comparingInt(Dictionary::getId));
        return result;
    }

    @RequestMapping("/sensor/save")
    @ResponseBody
    public SimpleResponse saveSensor(@RequestBody Sensor sensor){
        ru.rushydro.vniig.ias.dao.entity.Sensor saveSensor = sensorRepository
                .findById(sensor.getId()).orElse(null);
        if (saveSensor == null) {
            saveSensor = new ru.rushydro.vniig.ias.dao.entity.Sensor();
            saveSensor.setId(sensor.getId());
        }
        saveSensor.setName(sensor.getName());
        saveSensor.setType(sensor.getType());
        saveSensor.setOn(1);
        saveSensor.setTagName(sensor.getTagName());
        if (sensor.getObjMonitor() != null) {
            saveSensor.setObjectMonitor(objectMonitorRepository.findById(sensor.getObjMonitor().getId()).orElse(null));
        } else {
            saveSensor.setObjectMonitor(objectMonitorRepository.findById(1).orElse(null));
        }

        if (sensor.getPl302() != null) {
            saveSensor.setPl302(pl302Repository.findById(sensor.getPl302().getId()).orElse(null));
        }

        try {
            sensorRepository.save(saveSensor);
            return new SimpleResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse(false, e.getMessage());
        }
    }

    @RequestMapping("/signals")
    @ResponseBody
    public List<Signal> getSignals(){
        List<Signal> result = new ArrayList<>();
        signalRepository.findAll().forEach(entity -> {
            Signal signal = new Signal();
            signal.setId(entity.getId());
            Dictionary sensor = new Dictionary();
            sensor.setId(entity.getSensor().getId());
            sensor.setName(entity.getSensor().getName());
            signal.setSensor(sensor);
            Dictionary type = new Dictionary();
            type.setId(entity.getType().getId());
            type.setName(entity.getType().getName());
            signal.setType(type);
            Dictionary measureParam = new Dictionary();
            measureParam.setId(entity.getMeasuredParameter().getId());
            measureParam.setName(entity.getMeasuredParameter().getName());
            signal.setMeasureParam(measureParam);
            result.add(signal);
        });
        result.sort(Comparator.comparingInt(Signal::getId));
        return result;
    }

    @RequestMapping("/signal/save")
    @ResponseBody
    public SimpleResponse saveSignal(@RequestBody Signal signal){
        ru.rushydro.vniig.ias.dao.entity.Signal saveSignal = signalRepository
                .findById(signal.getId()).orElse(null);
        if (saveSignal == null) {
            saveSignal = new ru.rushydro.vniig.ias.dao.entity.Signal();
            saveSignal.setId(signal.getId());
        }
        if (signal.getSensor() != null && signal.getSensor().getId() != null) {
            saveSignal.setSensor(sensorRepository
                    .findById(signal.getSensor().getId()).orElse(null));
        }
        if (signal.getType() != null && signal.getType().getId() != null) {
            saveSignal.setType(signalTypeRepository
                    .findById(signal.getType().getId()).orElse(null));
        }
        if (signal.getMeasureParam() != null && signal.getMeasureParam().getId() != null) {
            saveSignal.setMeasuredParameter(measuredParameterRepository
                    .findById(signal.getMeasureParam().getId()).orElse(null));
        }
        try {
            signalRepository.save(saveSignal);
            return new SimpleResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse(false, e.getMessage());
        }
    }

    @RequestMapping("/pl302s")
    @ResponseBody
    public List<PL302> getPL302s(){
        List<PL302> result = new ArrayList<>();
        pl302Repository.findAll().forEach(entity -> {
            PL302 pl302 = new PL302();
            pl302.setId(entity.getId());
            pl302.setName(entity.getName());
            pl302.setUrl(entity.getUrl());
            pl302.setPassword(entity.getPassword());
            result.add(pl302);
        });
        result.sort(Comparator.comparingInt(PL302::getId));
        return result;
    }

    @RequestMapping("/pl302/save")
    @ResponseBody
    public SimpleResponse savePL302(@RequestBody PL302 pl302){
        ru.rushydro.vniig.ias.dao.entity.Pl302 savePL302 = pl302Repository
                .findById(pl302.getId()).orElse(null);
        if (savePL302 == null) {
            savePL302 = new ru.rushydro.vniig.ias.dao.entity.Pl302();
            savePL302.setId(pl302.getId());
        }
        savePL302.setName(pl302.getName());
        savePL302.setUrl(pl302.getUrl());
        savePL302.setPassword(pl302.getPassword());
        try {
            pl302Repository.save(savePL302);
            return new SimpleResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse(false, e.getMessage());
        }
    }
}
