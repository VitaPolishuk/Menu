package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.Employees;

import java.sql.Date;
import java.util.List;

public interface BasicDao {
    public void addBasic(Basic basic);

    public void ediBasic(Basic basic);

    public void removeBasic(Long id);

    public Basic getBasic(Long id);

    public List<Basic> listBasics();

    public void setComplex(Long idE, Long idR);

    public Employees  returnEmployeeByRecord(Long idRecord, boolean status);
}
