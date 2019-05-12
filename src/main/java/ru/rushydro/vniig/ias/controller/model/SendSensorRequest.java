package ru.rushydro.vniig.ias.controller.model;

import java.util.List;

public class SendSensorRequest {

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
