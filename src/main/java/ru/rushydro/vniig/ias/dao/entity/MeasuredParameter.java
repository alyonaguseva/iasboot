package ru.rushydro.vniig.ias.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yazik on 30.04.2017.
 */
@Entity
@Table(name = "measured_parameter")
public class MeasuredParameter {

    @Id
    private Integer id;

    private String name;

    private Integer dataType;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

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
}
