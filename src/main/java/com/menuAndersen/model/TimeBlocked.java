package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wital on 01.03.2017.
 */
@Entity
@Table(name = "timeblocked")
public class TimeBlocked implements Serializable{
    @Id
    @Column(name = "idTime")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTime;

    @Column(name = "currentTime")
    private String currentTime;

    @Column(name = "globalTime")
    private String globalTime;



    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

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
}
