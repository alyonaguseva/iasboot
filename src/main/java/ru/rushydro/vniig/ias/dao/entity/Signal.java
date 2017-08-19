package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "signal")
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
}
