package ru.rushydro.vniig.ias.model;

public class InterrogationSetting {

    private String inclinometersUrl;

    private Integer inclinometersTime;

    private String stringSensorsUrl;

    private Integer stringSensorsTime;

    private String stringSensorsList;

    public String getInclinometersUrl() {
        return inclinometersUrl;
    }

    public void setInclinometersUrl(String inclinometersUrl) {
        this.inclinometersUrl = inclinometersUrl;
    }

    public Integer getInclinometersTime() {
        return inclinometersTime;
    }

    public void setInclinometersTime(Integer inclinometersTime) {
        this.inclinometersTime = inclinometersTime;
    }

    public String getStringSensorsUrl() {
        return stringSensorsUrl;
    }

    public void setStringSensorsUrl(String stringSensorsUrl) {
        this.stringSensorsUrl = stringSensorsUrl;
    }

    public Integer getStringSensorsTime() {
        return stringSensorsTime;
    }

    public void setStringSensorsTime(Integer stringSensorsTime) {
        this.stringSensorsTime = stringSensorsTime;
    }

    public String getStringSensorsList() {
        return stringSensorsList;
    }

    public void setStringSensorsList(String stringSensorsList) {
        this.stringSensorsList = stringSensorsList;
    }
}
