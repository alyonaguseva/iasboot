package ru.rushydro.vniig.ias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rushydro.vniig.ias.dao.MeasuredParameterRepository;
import ru.rushydro.vniig.ias.dao.SensorRepository;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.types.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {
    
    private final MeasuredParameterRepository measuredParameterRepository;

    private final SensorRepository sensorRepository;

    private final SignalRepository signalRepository;

    @Autowired
    public ExchangeController(MeasuredParameterRepository measuredParameterRepository,
                              SensorRepository sensorRepository,
                              SignalRepository signalRepository) {
        this.measuredParameterRepository = measuredParameterRepository;
        this.sensorRepository = sensorRepository;
        this.signalRepository = signalRepository;
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
        return result;
    }

    @RequestMapping("/measure/add")
    @ResponseBody
    public SimpleResponse addMeasuresParam(@RequestParam(value = "measure") Measure measure){
        return null;
    }

    @RequestMapping("/measure/edit")
    @ResponseBody
    public SimpleResponse editMeasuresParam(@RequestParam(value = "measure")Measure measure){
        return null;
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
            sensor.setId(entity.getId());
            sensor.setName(entity.getName());
            sensor.setObjMonitor(objMon);
            sensor.setType(entity.getType());
            result.add(sensor);
        });
        return result;
    }

    @RequestMapping("/sensor/add")
    @ResponseBody
    public SimpleResponse addSensor(@RequestParam(value = "sensor")Sensor sensor){
        return null;
    }

    @RequestMapping("/sensor/edit")
    @ResponseBody
    public SimpleResponse editSensor(@RequestParam(value = "sensor")Sensor sensor){
        return null;
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
        return result;
    }

    @RequestMapping("/signal/add")
    @ResponseBody
    public SimpleResponse addSignal(@RequestParam(value = "signal")Signal signal){
        return null;
    }

    @RequestMapping("/signal/edit")
    @ResponseBody
    public SimpleResponse editSignal(@RequestParam(value = "signal")Signal signal){
        return null;
    }
}
