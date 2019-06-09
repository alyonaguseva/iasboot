package ru.rushydro.vniig.ias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rushydro.vniig.ias.dao.SignalValueExtRepository;
import ru.rushydro.vniig.ias.dao.entity.Log;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;
import ru.rushydro.vniig.ias.dao.specification.LogSpecification;
import ru.rushydro.vniig.ias.dao.specification.TaskLogSpecification;
import ru.rushydro.vniig.ias.model.PageCount;
import ru.rushydro.vniig.ias.model.Sensor;
import ru.rushydro.vniig.ias.model.Signal;
import ru.rushydro.vniig.ias.service.LogService;
import ru.rushydro.vniig.ias.service.SensorService;
import ru.rushydro.vniig.ias.service.TaskLogService;
import ru.rushydro.vniig.ias.types.SignalValueExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by yazik on 22.03.2017.
 */
@Controller
public class RestController {

    private static List<Signal> signals = new ArrayList<>();

    private static List<Sensor> sensors = new ArrayList<>();

    private final
    TaskLogService taskLogService;

    private final
    LogService logService;

    private final
    SensorService sensorService;

    private final SignalValueExtRepository signalValueExtRepository;

    private static String[] controlObjects = {"Ж/Б плотина", "Ж/Б плотина 2", "Ж/Б плотина 3"};
    private static String[] controlElements = {"Низовая грань", "Низовая грань 2", "Низовая грань 3"};
    private static String[] measurementTypes = {"Геодезические", "Геодезические 2", "Геодезические 3"};
    private static String[] sensorGroups = {"Трехосные щелемеры", "Трехосные щелемеры 2", "Трехосные щелемеры 3"};
    private static String[] sensorTypes = {"BL1", "BL2", "BL3"};

    static {
        IntStream.range(0, 200).forEach(i -> {
            Signal signal = new Signal();
            signal.setName("Сигнал " + i);
            signal.setSnippet("Описание сигнала " + i);
            signals.add(signal);
        });

        Random random = new Random();

        IntStream.range(0, 20).forEach(i -> {
            Sensor sensor = new Sensor();
            sensor.setName("Сенсор " + i);
            sensor.setOn(!(i % 3 == 0) ? 1 : 0);
            sensor.setControlObject(controlObjects[random.nextInt(3)]);
            sensor.setControlElement(controlElements[random.nextInt(3)]);
            sensor.setMeasurementType(measurementTypes[random.nextInt(3)]);
            sensor.setSensorGroup(sensorGroups[random.nextInt(3)]);
            sensor.setSensorType(sensorTypes[random.nextInt(3)]);
            sensors.add(sensor);
        });
    }

    @Autowired
    public RestController(TaskLogService taskLogService,
                          LogService logService, SensorService sensorService, SignalValueExtRepository signalValueExtRepository) {
        this.taskLogService = taskLogService;
        this.logService = logService;
        this.sensorService = sensorService;
        this.signalValueExtRepository = signalValueExtRepository;
    }

    @RequestMapping("/signals")
    @ResponseBody
    public Page<Signal> getSignals(Pageable pageable) {
        List<Signal> sigs = new ArrayList<>();
        for (int i = (pageable.getPageNumber() - 1) * 10;
             i < (pageable.getPageNumber() * 10) && i < signals.size(); i++) {
            sigs.add(signals.get(i));
        }

        return new PageImpl<>(sigs, pageable, signals.size());
    }

    @RequestMapping("/logs")
    @ResponseBody
    public Page<TaskLog> getLogs(Pageable pageable,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 @RequestParam(value = "success", required = false) String success) {

        return taskLogService.findAll(new TaskLogSpecification(startDate, endDate, success), pageable);
//        return taskLogService.findAll(pageable);
    }

    @RequestMapping("/text-logs")
    @ResponseBody
    public Page<Log> getTextLogs(Pageable pageable,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 @RequestParam(value = "type", required = false) String type) {

        return logService.findAll(new LogSpecification(startDate, endDate, type), pageable);
    }

    @RequestMapping("/signalsCount")
    @ResponseBody
    public PageCount getSignalsCount() {
        PageCount pageCount = new PageCount();
        pageCount.setCount((signals.size() % 10 == 0) ? (long)(signals.size() / 10) : (signals.size() / 10 + 1));
        return pageCount;
    }

    @RequestMapping("/sensors")
    @ResponseBody
    public List<Sensor> getSensors() {
        //        return sensors;
        return sensorService.getAll().stream().map(sensor -> {
            Sensor s = new Sensor();
            s.setId(sensor.getId());
            s.setName(sensor.getName());
            s.setControlObject(sensor.getObjectMonitor().getName());
            s.setSensorType(sensor.getType());
            s.setOn(sensor.getOn());
            return s;
        }).collect(Collectors.toList());
    }

    @RequestMapping("/signalvalueext")
    @ResponseBody
    public List<SignalValueExt> getSignalValueExt() {
        return signalValueExtRepository.findNew().stream().map(sve -> {
            SignalValueExt signalValueExt = new SignalValueExt();
            signalValueExt.setId(sve.getId());
            signalValueExt.setSignalId(sve.getSignalId());
            signalValueExt.setValue(sve.getValue());
            signalValueExt.setCalibrated(sve.getCalibrated());
            signalValueExt.setValueTime(sve.getValueTime());
            return signalValueExt;
        }).collect(Collectors.toList());
    }

    @RequestMapping("/controlObjects")
    @ResponseBody
    public List<String> getControlObjects() {
        return Arrays.asList(controlObjects);
    }

    @RequestMapping("/controlElements")
    @ResponseBody
    public List<String> getControlElements() {
        return Arrays.asList(controlElements);
    }

    @RequestMapping("/measurementTypes")
    @ResponseBody
    public List<String> getMeasurementTypes() {
        return Arrays.asList(measurementTypes);
    }

    @RequestMapping("/sensorGroups")
    @ResponseBody
    public List<String> getSensorGroups() {
        return Arrays.asList(sensorGroups);
    }

    @RequestMapping("/sensorTypes")
    @ResponseBody
    public List<String> getSensorTypes() {
        return Arrays.asList(sensorTypes);
    }

}
