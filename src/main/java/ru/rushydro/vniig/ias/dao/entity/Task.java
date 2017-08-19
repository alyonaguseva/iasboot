package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_ids")
    private Long idIDS;

    @ManyToOne
    @JoinColumn(name = "id_signal")
    private Signal signal;

    @ManyToOne
    @JoinColumn(name = "id_output_signal")
    private OutputSignal outputSignal;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "date_max")
    private LocalDateTime dateMax;

    @ManyToOne
    @JoinColumn(name = "status")
    private TaskStatus status;

    private boolean complete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdIDS() {
        return idIDS;
    }

    public void setIdIDS(Long idIDS) {
        this.idIDS = idIDS;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public OutputSignal getOutputSignal() {
        return outputSignal;
    }

    public void setOutputSignal(OutputSignal outputSignal) {
        this.outputSignal = outputSignal;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateMax() {
        return dateMax;
    }

    public void setDateMax(LocalDateTime dateMax) {
        this.dateMax = dateMax;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
