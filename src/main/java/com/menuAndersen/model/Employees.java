package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    public Employees(){

    }

    public Employees(String fio, String positionHeld) {
        this.fio = fio;
        this.positionHeld = positionHeld;
      }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "positionHeld")
    private String positionHeld;

    @Column(name = "numberComplex")
    private String numberComplex;



    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPositionHeld() {
        return positionHeld;
    }

    public void setPositionHeld(String positionHeld) {
        this.positionHeld = positionHeld;
    }

    public String getNumberComplex() {
        return numberComplex;
    }

    public void setNumberComplex(String numberComplex) {
        this.numberComplex = numberComplex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
