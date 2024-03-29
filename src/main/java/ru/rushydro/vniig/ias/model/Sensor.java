package ru.rushydro.vniig.ias.model;

/**
 * Класс описание датчиков
 * Created by yazik on 09.04.2017.
 */
public class Sensor {

    private Integer id;

    private String name;

    private Integer on = 1;

    private String controlObject;

    private String controlElement;

    private String measurementType;

    private String sensorGroup;

    private String sensorType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOn() {
        return on;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public String getControlObject() {
        return controlObject;
    }

    public void setControlObject(String controlObject) {
        this.controlObject = controlObject;
    }

    public String getControlElement() {
        return controlElement;
    }

    public void setControlElement(String controlElement) {
        this.controlElement = controlElement;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getSensorGroup() {
        return sensorGroup;
    }

    public void setSensorGroup(String sensorGroup) {
        this.sensorGroup = sensorGroup;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
