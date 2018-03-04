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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Сервис получения данных по тэгам
 */
@Service
public class TagService {

    private final static Logger log = Logger.getLogger(ParseFileService.class.getName());

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

        String url = tagUrl + "?read=" + tags;

        log.info("Получение данных датчиков по url: " + url);

        String tagValues = null;

        if (appProperties.getType() != null && appProperties.getType().equalsIgnoreCase("core")) {
            log.info("Получение данных датчиков с помощью java core");
            try {
                URL getUrl = new URL(url);
                HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
                con.setRequestMethod("GET");
                try (InputStream is = con.getInputStream();
                     BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    tagValues = response.toString();
                } catch (IOException e) {
                    log.warning("Ошибка запроса: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                log.warning("Ошибка запроса: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            ResponseEntity<String> responseEntity = rest.exchange(url,
                    HttpMethod.GET, requestEntity, String.class);
            tagValues =  responseEntity.getBody();
        }

        log.info("Данные получены. Значения датчиков: " + tagValues);

        if (tagValues != null) {
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
                            log.info("Id полученного датчика: " + signalValueExt.getSignalId() +
                                    " значение сигнала: " + signalValueExt.getValue());
                        }
                    }

                }
                log.info("Сохранение данных датчиков в базу данных.");
                signalValueExtService.saveAll(values);
                log.info("Сохранение данных датчиков в базу данных успешно.");
            } catch (IOException e) {
                log.warning("Ошибка разбора json: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
