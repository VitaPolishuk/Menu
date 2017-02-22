package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "basic")
public class Basic implements Serializable {

    @Id
    @Column(name = "idBasic")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBasic;

    @ManyToOne
    @JoinColumn(name = "idEmployee", nullable = false)
    private Employees idEmployee;

    @ManyToOne
    @JoinColumn(name = "idRecord")
    private DateAndComplexes idRecord;

    @Column(name = "status")
    private boolean status;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public Long getIdBasic() {
        return idBasic;
    }

    public void setIdBasic(Long idBasic) {
        this.idBasic = idBasic;
    }

    public Employees getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employees idEmployee) {
        this.idEmployee = idEmployee;
    }

    public DateAndComplexes getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(DateAndComplexes idRecord) {
        this.idRecord = idRecord;
    }
}