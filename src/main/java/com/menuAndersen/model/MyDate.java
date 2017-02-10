package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "mydate")
public class MyDate implements Serializable {

    @Id
    @Column(name = "idDate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDate;

    @Column(name = "date")
    private Date date;

    public Long getIdDate() {
        return idDate;
    }

    public void setIdDate(Long idDate) {
        this.idDate = idDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
