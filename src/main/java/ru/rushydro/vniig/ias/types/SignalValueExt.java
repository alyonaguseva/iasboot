package ru.rushydro.vniig.ias.types;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SignalValueExt {

    private Long id;

    private Integer signalId;

    private BigDecimal value;

    private Integer calibrated;

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
