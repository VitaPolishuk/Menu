package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {

    @Id
    @Column(name = "idDish")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDish;

    @Column(name = "nameDish") // наименование блюда
    private String nameDish;

    @Column(name = "typeDish") // тип блюда
    private String typeDish;

    @Column(name = "imgDish" , columnDefinition = "MEDIUMBLOB") // картинка
    private byte[] imgDish;

    public Dish() {
    }

    public Dish(String nameDish, String typeDish, byte[] imgDish) {
        this.nameDish = nameDish;
        this.typeDish = typeDish;
        this.imgDish = imgDish;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getTypeDish() {
        return typeDish;
    }

    public void setTypeDish(String typeDish) {
        this.typeDish = typeDish;
    }

    public byte[] getImgDish() {
        return imgDish;
    }

    public void setImgDish(byte[] imgDish) {
        this.imgDish = imgDish;
    }
}
