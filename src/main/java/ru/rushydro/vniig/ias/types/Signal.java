package ru.rushydro.vniig.ias.types;

public class Signal {

    private Integer id;

    private Dictionary sensor;

    private Dictionary type;

    private Dictionary measureParam;

    private String tagName;

    private Dictionary pl302;

    private Boolean inTag;

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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Dictionary getPl302() {
        return pl302;
    }

    public void setPl302(Dictionary pl302) {
        this.pl302 = pl302;
    }

    public void setInTag(Boolean inTag) {
        this.inTag = inTag;
    }

    public Boolean getInTag() {
        return inTag;
    }
}
