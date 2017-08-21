package ru.rushydro.vniig.ias.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "task_log")
public class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TaskLogType type;

    @ManyToOne
    @JoinColumn(name = "id_task")
    private Task task;

    private boolean success;

    @Column(name = "error_code")
    private int errorCode;

    private String comment;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskLogType getType() {
        return type;
    }

    public void setType(TaskLogType type) {
        this.type = type;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        String template = "%s";
        switch (type.getSystemname()) {
            case "NEW":
                template = "Получено новое задание получения данных датчика %s.";
                break;
            case "SENDTOSENSOR":
                template = "Задание о получение данных датчика %s отправлено на выполенение.";
                break;
            case "NEEDTOSEND":
                template = "Данные датчика %s получены. Ожидается отправка в буферную зону.";
                break;
            case "COMPLETE":
                template = "Отправка данных датчика %s в буферную зону.";
                break;
        }

        return String.format(template, task.getSignal().getSensor().getName());
    }
}
