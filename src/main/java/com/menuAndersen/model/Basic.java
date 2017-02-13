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
    @JoinColumn(name = "idDate", nullable = false)
    private MyDate idDate;
    @ManyToOne
    @JoinColumn(name = "idComplex", nullable = false)
    private DateAndComplexes idComplex;                //возможно здесь придется поменять

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

    public MyDate getIdDate() {
        return idDate;
    }

    public void setIdDate(MyDate idDate) {
        this.idDate = idDate;
    }

    public DateAndComplexes getIdComplex() {
        return idComplex;
    }

    public void setIdComplex(DateAndComplexes idComplex) {
        this.idComplex = idComplex;
    }
}