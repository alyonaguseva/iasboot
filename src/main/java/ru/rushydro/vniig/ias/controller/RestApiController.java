package ru.rushydro.vniig.ias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rushydro.vniig.ias.service.SignalValueExtRestService;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesRequest;
import ru.rushydro.vniig.ias.webservice.SendSignalValuesResponse;

@Controller
public class RestApiController {

    private final SignalValueExtRestService signalValueExtRestService;

    @Autowired
    public RestApiController(SignalValueExtRestService signalValueExtRestService) {
        this.signalValueExtRestService = signalValueExtRestService;
    }

    @RequestMapping(value = "/rest/sendSignalValues", method = RequestMethod.POST)
    public @ResponseBody SendSignalValuesResponse sendSignalValues(@RequestBody SendSignalValuesRequest request) {
        return signalValueExtRestService.sendSignalValues(request);
    }

}
