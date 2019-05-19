package ru.rushydro.vniig.ias.service;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rushydro.vniig.ias.AppProperties;
import ru.rushydro.vniig.ias.StringUtils;
import ru.rushydro.vniig.ias.dao.SignalRepository;
import ru.rushydro.vniig.ias.dao.entity.Pl302;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис получения данных по тэгам
 */
@Service
public class TagService {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(TagService.class.getName());

    @Value("${tag.service.path:null}")
    private String tagUrl;

    @Value("${tag.password:null}")
    private String tagPassword;

    @Value("${password.field.name:passb}")
    private String passwordFieldName;

    @Value("${password.button.tag:input}")
    private String passwordButtonTag;

    private final
    SensorService sensorService;

    private final
    SignalService signalService;

    private final
    AppProperties appProperties;

    private final HttpHeaders headers;

    private final SignalValueExtService signalValueExtService;

    private final SignalRepository signalRepository;

    @Autowired
    public TagService(SensorService sensorService, SignalService signalService, AppProperties appProperties,
                      SignalValueExtService signalValueExtService, SignalRepository signalRepository) {
        this.sensorService = sensorService;
        this.signalService = signalService;
        this.appProperties = appProperties;
        this.signalValueExtService = signalValueExtService;
        this.signalRepository = signalRepository;

        headers = new HttpHeaders();
    }

    public void processTags() {

        Pl302 pl302 = new Pl302();
        pl302.setId(-1);
        pl302.setUrl(tagUrl);
        pl302.setPassword(tagPassword);

        List<Signal> signals = signalService.findByInTag(true);


        Map<Integer, Pl302> pl302Map = new HashMap<>();
        Map<Integer, List<Signal>> sensorMap = new HashMap<>();

        signals.forEach(s -> {
            if (s.getPl302() == null) {
                s.setPl302(pl302);
            }
            pl302Map.putIfAbsent(s.getPl302().getId(), s.getPl302());

            List<Signal> list = sensorMap.computeIfAbsent(s.getPl302().getId(), k -> new ArrayList<>());
            list.add(s);
        });

        sensorMap.forEach((key, value) -> {
            List<Signal> temps = new ArrayList<>();

            for (int i = 0; i < value.size(); i++) {
                if (i % 4 == 0 && i > 0) {
                    processPackageTags(pl302Map.get(key), temps);
                    temps = new ArrayList<>();
                }
                temps.add(signals.get(i));
            }

            processPackageTags(pl302Map.get(key), temps);
        });


    }

    private void processPackageTags(Pl302 pl302, List<Signal> signals) {
        if (StringUtils.isNotEmpty(pl302.getUrl())) {
            String tags = signals.stream().map(signal ->
                    StringUtils.isNotEmpty(signal.getTagName()) ?
                            signal.getTagName() : appProperties.getTags().get(signal.getId().toString()))
                    .collect(Collectors.joining("__"));
            RestTemplate rest = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

            String url = pl302.getUrl() + "?read=" + tags;

            log.info("Получение данных датчиков по url: " + url);

            String tagValues = null;

            if (appProperties.getType() != null && appProperties.getType().equalsIgnoreCase("web")) {
                log.info("Получение данных датчиков с помощью java web");
                try {
                    WebClient webClient = new WebClient();
                    webClient.setJavaScriptEnabled(false);
                    HtmlPage currentPage = webClient.getPage(pl302.getUrl()); //Load page at the STRING address.
                    HtmlInput password = currentPage.getElementByName(passwordFieldName); //Find element called loginpassword for password
                    password.setValueAttribute(pl302.getPassword()); //Set value for password
                    List<HtmlElement> inputs = currentPage.getElementsByTagName(passwordButtonTag);
                    HtmlSubmitInput submitBtn = (HtmlSubmitInput) inputs.get(inputs.size() - 1); //Find element called Submit to submit form.
                    try {
                        currentPage = submitBtn.click(); //Click on the button.
                    } catch (Throwable e) {
                        log.debug("Пришла следующая ошибка: " + e.getMessage() + " прокускаем её. Считаем, что сайт не настроен на pl302.");
                    }
                    UnexpectedPage page = webClient.getPage(url);
                    page.getWebResponse().getContentAsString();
                    log.info("Получение страницы данных: " + page.getWebResponse().getContentAsString());
                    tagValues = page.getWebResponse().getContentAsString();
                } catch (IOException e) {
                    log.error("Ошибка запроса:", e);
                    e.printStackTrace();
                }
            } else if (appProperties.getType() != null && appProperties.getType().equalsIgnoreCase("web-noauth")) {
                log.info("Получение данных датчиков с помощью java web-noauth");
                try {
                    WebClient webClient = new WebClient();
                    webClient.setJavaScriptEnabled(false);
                    UnexpectedPage page = webClient.getPage(url);
                    page.getWebResponse().getContentAsString();
                    log.info("Получение страницы данных: " + page.getWebResponse().getContentAsString());
                    tagValues = page.getWebResponse().getContentAsString();
                } catch (IOException e) {
                    log.error("Ошибка запроса:", e);
                    e.printStackTrace();
                }
            } else if (appProperties.getType() != null && appProperties.getType().equalsIgnoreCase("core")) {
                log.info("Получение данных датчиков с помощью java core");
                try {
                    URL getUrl = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
                    String userCredentials = ":" + pl302.getPassword();
                    String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
                    con.setRequestProperty ("Authorization", basicAuth);
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(5 * 60 * 1000);
                    con.setReadTimeout(60 * 1000);
                    con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    con.setRequestProperty("Accept-Encoding", "gzip, deflate");
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
                        log.error("Ошибка запроса:", e);
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    log.error("Ошибка запроса:", e);
                    e.printStackTrace();
                }

            } else if (appProperties.getType() != null && appProperties.getType().equalsIgnoreCase("core-noauth")) {
                log.info("Получение данных датчиков с помощью java core-noauth");
                try {
                    URL getUrl = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(5 * 60 * 1000);
                    con.setReadTimeout(60 * 1000);
                    con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    con.setRequestProperty("Accept-Encoding", "gzip, deflate");
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
                        log.error("Ошибка запроса:", e);
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    log.error("Ошибка запроса:", e);
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
                    for (Signal signal : signals) {
                        if (signal != null) {
                            String tag = StringUtils.isNotEmpty(signal.getTagName()) ? signal.getTagName()
                                    : appProperties.getTags().get(signal.getId().toString());
                            TreeNode node = actualObj.get(tag);
                            if (node != null && node.toString() != null
                                    && !node.toString().replaceAll("\"", "").isEmpty()) {
                                SignalValueExt signalValueExt = new SignalValueExt();
                                signalValueExt.setValue(new BigDecimal(node.toString()
                                        .replaceAll("\"", "")));
                                signalValueExt.setSignalId(signal.getId());
                                signalValueExt.setCalibrated(1);
                                signalValueExt.setValueTime(LocalDateTime.now());
                                values.add(signalValueExt);
                                log.info("Id полученного датчика: " + signalValueExt.getSignalId() +
                                        " значение сигнала: " + signalValueExt.getValue());
                            }
                        }

                    }
                    log.info("Сохранение данных датчиков в базу данных.");
                    signalValueExtService.saveAll(values);
                    log.info("Сохранение данных датчиков в базу данных успешно.");
                } catch (NumberFormatException e) {
                    log.error("Ошибка разбора json. Получены значения датчиков в невверном формате: " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    log.error("Ошибка разбора json: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            log.info("Путь к pl302 не указан");
        }

    }

}
