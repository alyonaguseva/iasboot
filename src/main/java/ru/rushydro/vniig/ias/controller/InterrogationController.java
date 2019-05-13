package ru.rushydro.vniig.ias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rushydro.vniig.ias.model.InterrogationSetting;
import ru.rushydro.vniig.ias.service.InterrogationService;
import ru.rushydro.vniig.ias.types.SimpleResponse;

@RequestMapping("/interrogation")
@Controller
public class InterrogationController {

    private final static Logger log = LoggerFactory.getLogger(InterrogationController.class);

    private final InterrogationService interrogationService;

    public InterrogationController(InterrogationService interrogationService) {
        this.interrogationService = interrogationService;
    }

    @RequestMapping("/setting/get")
    public @ResponseBody
    InterrogationSetting getSetting() {
        return interrogationService.getSetting();
    }

    @RequestMapping("/setting/save")
    public @ResponseBody
    SimpleResponse saveSetting(@RequestBody InterrogationSetting interrogationSetting) {
        try {
            interrogationService.saveSetting(interrogationSetting);
            return new SimpleResponse(true);
        } catch (Exception e) {

            return new SimpleResponse(false, "Ошибка при сохранение настроек");
        }
    }
}
