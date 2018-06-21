package ru.rushydro.vniig.ias.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.entity.AppData;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис по обработке файла
 * Created by yazik on 04.11.2017.
 */
@Service
public class ParseFileService {
    private final static Logger log = LoggerFactory.getLogger(ParseFileService.class.getName());

    private final
    SignalValueExtService signalValueExtService;

    private final
    SignalService signalService;

    private final
    AppDataService appDataService;

    @Value("${file.path}")
    private String filePath;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Autowired
    public ParseFileService(SignalValueExtService signalValueExtService, SignalService signalService, AppDataService appDataService) {
        this.signalValueExtService = signalValueExtService;
        this.signalService = signalService;
        this.appDataService = appDataService;
    }

    public void parseFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (Reader in = new FileReader(filePath);
                 CSVReader reader = new CSVReaderBuilder(in).withSkipLines(1).build()) {
                Iterator<String[]> records = reader.iterator();
                Map<Integer, Integer> datas = new HashMap<>();
                List<SignalValueExt> values;

                String[] record = records.next();
                if (record != null) {
                    for (int j = 0; j < record.length; j++) {
                        String str = record[j];
                        if (str != null
                                && !str.isEmpty()
                                && !str.equalsIgnoreCase("NAN")) {
                            datas.put(j, Integer.parseInt(str));
                        }
                    }
                }

                String[] lastRecord = null;

                while (records.hasNext()) {
                    lastRecord = records.next();
                }
                if (lastRecord != null) {
                    AppData appData = appDataService.findById(1);
                    if (appData == null) {
                        appData = new AppData();
                        appData.setId(1);
                        appData.setName("lastParseRow");
                        appData.setValue("0");
                    }
                    String lastNumber = lastRecord[0];
                    if (lastNumber != null && !lastNumber.equalsIgnoreCase(appData.getValue())) {
                        appData.setValue(lastNumber);
                        values = new ArrayList<>();
                        for (int j = 0; j < lastRecord.length; j++) {
                            Integer signalId = datas.get(j);
                            String value = lastRecord[j];
                            if (signalId != null &&
                                    value != null
                                    && !value.isEmpty()
                                    && !value.equalsIgnoreCase("NAN")) {
                                Integer sId = signalId > 999 ? signalId / 10 : signalId;
                                Signal signal = signalService.findById(sId);
                                if (signal != null) {
                                    SignalValueExt signalValueExt = new SignalValueExt();
                                    signalValueExt.setValue(new BigDecimal(value));
                                    signalValueExt.setSignalId(sId);
                                    signalValueExt.setCalibrated(signalId > 999 ? 1 : 0);
                                    signalValueExt.setValueTime(LocalDateTime.now());
                                    values.add(signalValueExt);
                                } else {
                                    log.warn("Сигнал с id : " + sId + " при разборе файла не найден!");
                                }
                            }
                        }
                        if (!values.isEmpty()) {
                            signalValueExtService.saveAll(values);
                            appDataService.save(appData);
                        }
                    }

                }

            } catch (Exception e) {
                log.info("Ошибка при разборе файла: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            log.info("Файл для получения данных датчиков не существует. Путь к файлу: "
                    + file.getAbsolutePath());
        }
    }

    public void insertTestData() {
        File file = new File(filePath);
        if (file.exists()) {
            try (Reader in = new FileReader(filePath);
                 CSVReader reader = new CSVReaderBuilder(in).withSkipLines(1).build()) {
                Iterator<String[]> records = reader.iterator();
                Map<Integer, Integer> datas = new HashMap<>();

                String[] record = records.next();
                if (record != null) {
                    for (int j = 0; j < record.length; j++) {
                        String str = record[j];
                        if (str != null
                                && !str.isEmpty()
                                && !str.equalsIgnoreCase("NAN")) {
                            datas.put(j, Integer.parseInt(str));
                        }
                    }
                }

                String[] lastRecord = null;

                while (records.hasNext()) {
                    lastRecord = records.next();
                }
                if (lastRecord != null) {
                    Random random = new Random();

                    try {
                        String str = Integer.parseInt(lastRecord[0]) + 1 + ","
                                + LocalDateTime.now().format(dtf) + "," +
                                random.doubles(datas.size()).mapToObj(d -> ""
                                        + BigDecimal.valueOf(d).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue())
                                        .collect(Collectors.joining(",")) + "\r\n";
                        Files.write(file.toPath(), str.getBytes(), StandardOpenOption.APPEND);

                    } catch (Exception e) {
                        log.info("Ошибка записи тестовых данных в файл: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                log.info("Ошибка при разборе файла: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
