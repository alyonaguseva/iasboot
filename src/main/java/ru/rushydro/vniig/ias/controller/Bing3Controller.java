package ru.rushydro.vniig.ias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rushydro.vniig.ias.service.Bing3ExchangeService;
import ru.rushydro.vniig.ias.types.SimpleResponse;

@RequestMapping("/bing3")
@Controller
public class Bing3Controller {

    private final Bing3ExchangeService bing3ExchangeService;

    public Bing3Controller(Bing3ExchangeService bing3ExchangeService) {
        this.bing3ExchangeService = bing3ExchangeService;
    }

    @RequestMapping("/exchange")
    public @ResponseBody
    SimpleResponse saveSetting() {
        try {
            bing3ExchangeService.updateData();
            return new SimpleResponse(true);
        } catch (Exception e) {

            return new SimpleResponse(false, "Ошибка при взаимодействии с Bing3");
        }
    }
}
