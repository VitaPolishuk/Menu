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

    @Column(name = "currentTime")
    private String currentTime;

    @Column(name = "globalTime")
    private String globalTime;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getGlobalTime() {
        return globalTime;
    }

    public void setGlobalTime(String globalTime) {
        this.globalTime = globalTime;
    }

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
