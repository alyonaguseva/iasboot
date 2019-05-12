package ru.rushydro.vniig.ias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rushydro.vniig.ias.controller.model.SendSensorRequest;
import ru.rushydro.vniig.ias.model.TestTagContainer;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/tags")
    public @ResponseBody
    TestTagContainer testTag(@RequestParam(value = "read") String read) {
        System.out.println("read = " + read);

        TestTagContainer tagContainer = new TestTagContainer();
        tagContainer.setTag1("12");
        tagContainer.setTag2("15");
        tagContainer.setTag3("187");
        tagContainer.setTag4("1123");
        return tagContainer;
    }

    @RequestMapping(value = "/sendSensor", method = RequestMethod.POST)
    public @ResponseBody String testSendSensor(@RequestBody(required = false) SendSensorRequest request) {
        return "true";
    }

}
