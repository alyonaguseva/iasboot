package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "output_signal_value")
public class OutputSignalValue {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_signal")
    private OutputSignal signal;

    private int value;

    private String comment;

    @Column(name = "error_code")
    private int errorCode;

    @Column(name = "value_time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "id_task")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OutputSignal getSignal() {
        return signal;
    }

    public void setSignal(OutputSignal signal) {
        this.signal = signal;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
