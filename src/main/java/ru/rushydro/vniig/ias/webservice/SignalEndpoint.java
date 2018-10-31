package ru.rushydro.vniig.ias.webservice;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.rushydro.vniig.ias.service.SignalValueExtRestService;
import ru.rushydro.vniig.ias.service.TagService;

@Endpoint
public class SignalEndpoint {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(TagService.class.getName());

    private static final String NAMESPACE_URI = "http://rushydro.ru/vniig/ias/webservice";

    private final SignalValueExtRestService signalValueExtRestService;

    @Autowired
    public SignalEndpoint(SignalValueExtRestService signalValueExtRestService) {
        this.signalValueExtRestService = signalValueExtRestService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendSignalValuesRequest")
    @ResponsePayload
    public SendSignalValuesResponse sendSignalValues(@RequestPayload SendSignalValuesRequest request) {
        return signalValueExtRestService.sendSignalValues(request);
    }

}
