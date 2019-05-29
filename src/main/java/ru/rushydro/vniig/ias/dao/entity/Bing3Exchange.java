package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bing3_exchange")
public class Bing3Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_external_signal")
    private String idExternalSignal;

    private BigDecimal value;

    @Column(name = "calculated_value")
    private BigDecimal calculatedValue;

    @Column(name = "value_time")
    private LocalDateTime valueTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdExternalSignal() {
        return idExternalSignal;
    }

    public void setIdExternalSignal(String idExternalSignal) {
        this.idExternalSignal = idExternalSignal;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public LocalDateTime getValueTime() {
        return valueTime;
    }

    public void setValueTime(LocalDateTime valueTime) {
        this.valueTime = valueTime;
    }
}
