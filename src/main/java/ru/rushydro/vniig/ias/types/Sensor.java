package ru.rushydro.vniig.ias.types;

public class Sensor {

    Integer id;

    String name;

    Dictionary objMonitor;

    String type;



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
}
