package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dateAndComplexes")
public class DateAndComplexes implements Serializable {
    @Id
    @Column(name = "idDateComplex")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDateComplex;

   @ManyToOne
    @JoinColumn(name = "idDate", nullable = false)
    private MyDate idDate;

    @ManyToOne
    @JoinColumn(name = "idComplex", nullable = false)
    private Complexes idComplex;

    public Long getIdDateComplex() {
        return idDateComplex;
    }

    public void setIdDateComplex(Long idDateComplex) {
        this.idDateComplex = idDateComplex;
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
