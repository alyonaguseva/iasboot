package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "signal")
@SecondaryTables(
        {
                @SecondaryTable(name = "signal_setting", pkJoinColumns={@PrimaryKeyJoinColumn(name="id", referencedColumnName="id") }),
        @SecondaryTable(name = "bing3_signal_mapping", pkJoinColumns={@PrimaryKeyJoinColumn(name="id", referencedColumnName="id") })}
)

public class Signal {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_sensor")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private SignalType type;

    @ManyToOne
    @JoinColumn(name = "id_measured_parameter")
    private MeasuredParameter measuredParameter;

    @Column(name = "in_tag")
    private Boolean inTag;

    @Column(name = "tag_name", table = "signal_setting")
    private String tagName;

    @JoinColumn(name = "id_pl302", table = "signal_setting")
    @ManyToOne
    private Pl302 pl302;

    @Column(name = "id_external_signal", table = "bing3_signal_mapping")
    private String idExternalSignal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SignalType getType() {
        return type;
    }

    public void setType(SignalType type) {
        this.type = type;
    }

    public MeasuredParameter getMeasuredParameter() {
        return measuredParameter;
    }

    public void setMeasuredParameter(MeasuredParameter measuredParameter) {
        this.measuredParameter = measuredParameter;
    }

    public Boolean getInTag() {
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

    public String getIdExternalSignal() {
        return idExternalSignal;
    }

    public void setIdExternalSignal(String idExternalSignal) {
        this.idExternalSignal = idExternalSignal;
    }
}
