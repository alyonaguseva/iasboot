package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by yazik on 04.11.2017.
 */
@Entity
@Table(name = "signal_value_ext")
public class SignalValueExt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_signal")
    private Integer signalId;

    private BigDecimal value;

    private Integer calibrated;

    @Column
    private LocalDateTime valueTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSignalId() {
        return signalId;
    }

    public void setSignalId(Integer signalId) {
        this.signalId = signalId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getCalibrated() {
        return calibrated;
    }

    public void setCalibrated(Integer calibrated) {
        this.calibrated = calibrated;
    }

    public LocalDateTime getValueTime() {
        return valueTime;
    }

    public void setValueTime(LocalDateTime valueTime) {
        this.valueTime = valueTime;
    }
}
