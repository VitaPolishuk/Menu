package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "password")
public class Password implements Serializable {
    @Id
    @Column(name = "idPassword")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPassword;

    @Column(name = "password")
    private String password;

    public Long getIdPassword() {
        return idPassword;
    }


    public void setIdPassword(Long idPassword) {
        this.idPassword = idPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
