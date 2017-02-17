package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "complexes")
public class Complexes  implements Serializable {
    @Id
    @Column(name = "idComplex")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComplex;

    @Column(name = "firstCourse") // первое блюдо
    private String firstCourse;

    @Column(name = "secondCourse") // второе блюдо
    private String secondCourse;

    @Column(name = "salad") // салат
    private String salad;

    @Column(name = "drinks") // напитки
    private String drinks;

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

    public String getFirstCourse() {
        return firstCourse;
    }

    public void setFirstCourse(String firstCourse) {
        this.firstCourse = firstCourse;
    }

    public String getSecondCourse() {
        return secondCourse;
    }

    public void setSecondCourse(String secondCourse) {
        this.secondCourse = secondCourse;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }
}
