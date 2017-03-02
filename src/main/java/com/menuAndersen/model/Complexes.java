package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "complexes")
public class Complexes implements Serializable {
    @Id
    @Column(name = "idComplex")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComplex;

    @Column(name = "number") // номер
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getIdComplex() {
        return idComplex;
    }

    public void setIdComplex(Long idComplex) {
        this.idComplex = idComplex;
    }
}
