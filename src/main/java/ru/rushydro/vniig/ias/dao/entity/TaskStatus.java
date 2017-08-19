package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Cacheable
@Table(name = "task_status")
public class TaskStatus {

    @Id
    private Integer id;

    private String name;

    private String systemname;

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

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }
}
