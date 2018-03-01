package ru.rushydro.vniig.ias.service;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rushydro.vniig.ias.AppProperties;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Sensor;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Сервис получения данных по тэгам
 */
@Service
public class TagService {

    private static Logger log = Logger.getLogger(TagService.class.getName());

    @Value("${tag.service.path}")
    private String tagUrl;

    private final
    SensorService sensorService;

    private final
    AppProperties appProperties;

    private final HttpHeaders headers;

    private final SignalValueExtService signalValueExtService;

    private final SignalRepository signalRepository;

    @Autowired
    public TagService(SensorService sensorService, AppProperties appProperties, SignalValueExtService signalValueExtService, SignalRepository signalRepository) {
        this.sensorService = sensorService;
        this.appProperties = appProperties;
        this.signalValueExtService = signalValueExtService;
        this.signalRepository = signalRepository;

        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public void processTags() {

        List<Sensor> sensors = sensorService.findByInTag(true);
        List<Sensor> temps = new ArrayList<>();

        for (int i = 0; i < sensors.size(); i++) {
            if (i % 4 == 0 && i > 0) {
                processPackageTags(temps);
                temps = new ArrayList<>();
            }
            temps.add(sensors.get(i));
        }

        processPackageTags(temps);
    }

    private void processPackageTags(List<Sensor> sensors) {

        String tags = sensors.stream().map(sensor ->
                appProperties.getTags().get(sensor.getId().toString()))
                .collect(Collectors.joining("__"));
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> responseEntity = rest.exchange(tagUrl + "?read=" + tags,
                HttpMethod.GET, requestEntity, String.class);
        String tagValues =  responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj = mapper.readTree(tagValues);
            List<SignalValueExt> values = new ArrayList<>();
            for (Sensor sensor : sensors) {
                Signal signal = signalRepository.findBySensor(sensor);
                if (signal != null) {
                    String tag = appProperties.getTags().get(sensor.getId().toString());
                    TreeNode node = actualObj.get(tag);
                    if (node != null && node.toString() != null
                            && !node.toString().replaceAll("\"", "").isEmpty()) {
                        SignalValueExt signalValueExt = new SignalValueExt();
                        signalValueExt.setValue(new BigDecimal(node.toString()
                                .replaceAll("\"", "")));
                        signalValueExt.setSignalId(signal.getId());
                        signalValueExt.setCalibrated(0);
                        values.add(signalValueExt);
                    }
                }

            }
            signalValueExtService.saveAll(values);
        } catch (IOException e) {
            log.warning("Ошибка разбора json" + e.getMessage());
            e.printStackTrace();
        }
    }

}
