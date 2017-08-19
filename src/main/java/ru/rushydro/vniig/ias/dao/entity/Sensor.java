package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    private Integer id;

    private String name;

    @JoinColumn(name = "id_object_monitor")
    @ManyToOne
    private ObjectMonitor objectMonitor;

    private String type;

    @Column(name = "on_sensor")
    private boolean on;

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

    public ObjectMonitor getObjectMonitor() {
        return objectMonitor;
    }

    public void setObjectMonitor(ObjectMonitor objectMonitor) {
        this.objectMonitor = objectMonitor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
