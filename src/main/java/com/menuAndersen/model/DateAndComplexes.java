package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dateAndComplexes")
public class DateAndComplexes implements Serializable{
    @Id
    @Column(name = "idRecord")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecord;

    @ManyToOne
    @JoinColumn(name = "idDate", nullable = false)
    private MyDate idDate;

    @ManyToOne
    @JoinColumn(name = "idComplex", nullable = false)
    private Complexes idComplex;

    @ManyToOne
    @JoinColumn(name = "idDish", nullable = false)
    private Dish idDish;

    public DateAndComplexes() {
    }

    public DateAndComplexes(MyDate idDate, Complexes idComplex, Dish idDish) {
        this.idDate = idDate;
        this.idComplex = idComplex;
        this.idDish = idDish;
    }

    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish idDish) {
        this.idDish = idDish;
    }

    public Long getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Long idRecord) {
        this.idRecord = idRecord;
    }

    public MyDate getIdDate() {
        return idDate;
    }

    public void setIdDate(MyDate idDate) {
        this.idDate = idDate;
    }

    public Complexes getIdComplex() {
        return idComplex;
    }

    public void setIdComplex(Complexes idComplex) {
        this.idComplex = idComplex;
    }
}
