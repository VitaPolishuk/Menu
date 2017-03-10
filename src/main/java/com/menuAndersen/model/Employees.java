package com.menuAndersen.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    @Id
    @Column(name = "idEmployee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @Column(name = "fio")
    private String fio;

    @Column(name = "positionHeld")
    private String positionHeld;

    @Column(name = "status")
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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


    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "idEmployee=" + idEmployee +
                ", fio='" + fio + '\'' +
                ", positionHeld='" + positionHeld + '\'' +
                ", status=" + status +
                '}';
    }
}
