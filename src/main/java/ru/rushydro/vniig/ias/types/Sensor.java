package ru.rushydro.vniig.ias.types;

public class Sensor {

    Integer id;

    String name;

    Dictionary objMonitor;

    String type;

    String tagName;

    Dictionary pl302;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dictionary getObjMonitor() {
        return objMonitor;
    }

    public void setObjMonitor(Dictionary objMonitor) {
        this.objMonitor = objMonitor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
