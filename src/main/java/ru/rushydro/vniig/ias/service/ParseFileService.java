package ru.rushydro.vniig.ias.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.ias.dao.entity.AppData;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Сервис по обработке файла
 * Created by yazik on 04.11.2017.
 */
@Service
public class ParseFileService {
    private final static Logger log = Logger.getLogger(ParseFileService.class.getName());

    private final
    SignalValueExtService signalValueExtService;

    private final
    SignalService signalService;

    private final
    AppDataService appDataService;

    @Value("${file.path}")
    private String filePath;

    @Autowired
    public ParseFileService(SignalValueExtService signalValueExtService, SignalService signalService, AppDataService appDataService) {
        this.signalValueExtService = signalValueExtService;
        this.signalService = signalService;
        this.appDataService = appDataService;
    }

    public void parseFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (Reader in = new FileReader(filePath)) {
                Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
                int i = 0;
                Map<Integer, Integer> datas = new HashMap<>();
                List<SignalValueExt> values;
                CSVRecord lastRecord = null;
                for (CSVRecord record : records) {
                    if (i > 1) {
                        lastRecord = record;
                    } else if (i == 1) {
                        for (int j = 0; j < record.size(); j++) {
                            String str = record.get(j);
                            if (str != null
                                    && !str.isEmpty()
                                    && !str.equalsIgnoreCase("NAN")) {
                                datas.put(j, Integer.parseInt(str));
                            }
                        }
                    }
                    i++;
                }
                if (lastRecord != null) {
                    AppData appData = appDataService.findById(1);
                    if (appData == null) {
                        appData = new AppData();
                        appData.setId(1);
                        appData.setName("lastParseRow");
                        appData.setValue("0");
                    }
                    String lastNumber = lastRecord.get(0);
                    if (lastNumber != null && !lastNumber.equalsIgnoreCase(appData.getValue())) {
                        appData.setValue(lastNumber);
                        values = new ArrayList<>();
                        for (int j = 0; j < lastRecord.size(); j++) {
                            Integer signalId = datas.get(j);
                            String value = lastRecord.get(j);
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
                                    values.add(signalValueExt);
                                } else {
                                    log.warning("Сигнал с id : " + sId + " при разборе файла не найден!");
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

    private void clearFile() {
        File file = new File(filePath);
        File newFile = new File(filePath + "_old_" + new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()));
        try {
            Files.copy(file.toPath(), newFile.toPath());
            try (FileReader fr = new FileReader(newFile);
                BufferedReader br = new BufferedReader(fr);
                 FileWriter writer = new FileWriter(file)) {
                writer.write(br.readLine());
                writer.write(br.readLine());
            } catch (IOException e) {
                log.info("Ошибка очистки файла");
                e.printStackTrace();
            }
        } catch (IOException e) {
            log.info("Ошибка копирования файла");
            e.printStackTrace();
        }
    }
}
