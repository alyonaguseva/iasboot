package ru.rushydro.vniig.ias.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.StringUtils;
import ru.rushydro.vniig.ias.dao.entity.AppData;
import ru.rushydro.vniig.ias.model.InterrogationSetting;

import java.util.Calendar;
import java.util.Date;

@Service
public class InterrogationService {

    private final static Logger log = LoggerFactory.getLogger(InterrogationService.class);

    private Date interrogationInclinometersDate;

    private Date interrogationStringSensorsDate;

    private Date bing3ExchangeDate;

    @Autowired
    private AppDataService appDataService;

    @Value("${bing3.exchange.time:60000}")
    private Integer bing3ExchangeTime;

    @Autowired
    private LogService logService;

    public void interrogationInclinometers() {
        AppData appData = appDataService.findByName("inclinometersUrl");
        if (appData != null && StringUtils.isNotEmpty(appData.getValue())) {
            logService.info("Запрос получения данных инклинометров по ссылке: " + appData.getValue());
            pingUrl(appData.getValue(), null);
        }
    }

    public void interrogationStringSensors() {
        AppData appData = appDataService.findByName("stringSensorsUrl");
        if (appData != null && StringUtils.isNotEmpty(appData.getValue())) {

            String json;
            AppData sensorData = appDataService.findByName("stringSensorsList");

            if (StringUtils.isNotEmpty(sensorData.getValue())) {
                json = "{\"ids\":[" + sensorData.getValue() + "]}";
            } else {
                json = "{\"ids\":[]}";
            }

            logService.info("Запрос получения данных струнных датчиков по ссылке: " + appData.getValue());
            pingUrl(appData.getValue(), json);
        }
    }

    public Date getInterrogationInclinometersNext() {
        return getNextTime(interrogationInclinometersDate, "inclinometersTime");
    }

    public Date getinterrogationStringSensorsNext() {
        return getNextTime(interrogationStringSensorsDate, "stringSensorsTime");
    }

    public Date getBing3ExchangeNext() {
        return getNextTime(bing3ExchangeDate, "bing3ExchangeTime", bing3ExchangeTime);
    }

    private Date getNextTime(Date date, String propertyName) {
        return getNextTime(date, propertyName, 60000);
    }

    private Date getNextTime(Date date, String propertyName, Integer defaultTime) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int time = defaultTime;
        AppData appData = appDataService.findByName(propertyName);
        if (appData != null && StringUtils.isNotEmpty(appData.getValue()) && appData.getValue().matches("\\d+")) {
            time = Integer.parseInt(appData.getValue());
        }
        calendar.add(Calendar.MILLISECOND, time);
        date = calendar.getTime();
        return date;
    }

    private void pingUrl(String url, String json) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            if (StringUtils.isNotEmpty(json)) {
                StringEntity params = new StringEntity(json);
                request.setHeader("Content-type", "application/json");
                request.setEntity(params);
            }

            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                logService.error("Ошибка отправки сообщения по url:" + url + ". Код ошибки: " + response.getStatusLine().getStatusCode());
                log.error("Ошибка отправки сообщения по url. Код ошибки: " + response.getStatusLine().getStatusCode());
            }

        } catch (Exception e) {
            logService.error("Ошибка отправки сообщения по url: " + url + ". " + e.getMessage());
            log.error("Ошибка отправки сообщения по url: " + url, e);
            e.printStackTrace();
        }
    }

    public InterrogationSetting getSetting() {
        InterrogationSetting setting = new InterrogationSetting();
        AppData data = appDataService.findByName("inclinometersUrl");
        if (data != null && StringUtils.isNotEmpty(data.getValue())) {
            setting.setInclinometersUrl(data.getValue());
        }

        data = appDataService.findByName("inclinometersTime");
        if (data != null && StringUtils.isNotEmpty(data.getValue())) {
            setting.setInclinometersTime(Integer.parseInt(data.getValue()));
        }

        data = appDataService.findByName("stringSensorsUrl");
        if (data != null && StringUtils.isNotEmpty(data.getValue())) {
            setting.setStringSensorsUrl(data.getValue());
        }

        data = appDataService.findByName("stringSensorsTime");
        if (data != null && StringUtils.isNotEmpty(data.getValue())) {
            setting.setStringSensorsTime(Integer.parseInt(data.getValue()));
        }

        data = appDataService.findByName("stringSensorsList");
        if (data != null && StringUtils.isNotEmpty(data.getValue())) {
            setting.setStringSensorsList(data.getValue());
        }

        data = appDataService.findByName("bing3ExchangeTime");
        if (data != null  && StringUtils.isNotEmpty(data.getValue())) {
            setting.setBing3ExchangeTime(Integer.parseInt(data.getValue()));
        }

        return setting;
    }

    public void saveSetting(InterrogationSetting interrogationSetting) {
        saveData(3, "inclinometersUrl", interrogationSetting.getInclinometersUrl());
        saveData(4, "inclinometersTime", interrogationSetting.getInclinometersTime() != null
                ? interrogationSetting.getInclinometersTime().toString() : null);
        saveData(5, "stringSensorsUrl", interrogationSetting.getStringSensorsUrl());
        saveData(6, "stringSensorsTime", interrogationSetting.getStringSensorsTime() != null
                ? interrogationSetting.getStringSensorsTime().toString() : null);
        saveData(7, "stringSensorsList", interrogationSetting.getStringSensorsList());
        saveData(8, "bing3ExchangeTime", interrogationSetting.getBing3ExchangeTime() != null
                ? interrogationSetting.getBing3ExchangeTime().toString() : null);
    }

    private void saveData(Integer id, String propertyName, String value) {
        AppData data = appDataService.findByName(propertyName);
        if (data != null) {
            data.setValue(value);
        } else {
            data = new AppData();
            data.setId(id);
            data.setName(propertyName);
            data.setValue(value);
        }
        appDataService.save(data);
    }
}
