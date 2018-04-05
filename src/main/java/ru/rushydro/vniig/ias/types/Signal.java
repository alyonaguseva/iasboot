package ru.rushydro.vniig.ias.types;

public class Signal {

    Integer id;

    Dictionary sensor;

    Dictionary type;

    Dictionary measureParam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dictionary getSensor() {
        return sensor;
    }

    public void setSensor(Dictionary sensor) {
        this.sensor = sensor;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public Dictionary getMeasureParam() {
        return measureParam;
    }

    public void setMeasureParam(Dictionary measureParam) {
        this.measureParam = measureParam;
    }
}
