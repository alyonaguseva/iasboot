package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "sensor")
@SecondaryTable(name = "sensor_setting", pkJoinColumns={@PrimaryKeyJoinColumn(name="id", referencedColumnName="id") })
public class Sensor {

    @Id
    private Integer id;

    private String name;

    @JoinColumn(name = "id_object_monitor")
    @ManyToOne
    private ObjectMonitor objectMonitor;

    private String type;

    @Column(name = "on_sensor")
    private Integer on = 0;

    @Column(name = "in_tag")
    private Boolean inTag;

    @Column(name = "tag_name", table = "sensor_setting")
    private String tagName;

    @JoinColumn(name = "id_pl302", table = "sensor_setting")
    @ManyToOne
    private Pl302 pl302;

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

    public Integer getOn() {
        return on;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public Boolean isInTag() {
        return inTag;
    }

    public void setInTag(Boolean inTag) {
        this.inTag = inTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Pl302 getPl302() {
        return pl302;
    }

    public void setPl302(Pl302 pl302) {
        this.pl302 = pl302;
    }
}
