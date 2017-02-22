package com.menuAndersen.service;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.Employees;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface BasicService {
    public void addBasic(Basic basic);

    public void ediBasic(Basic basic);

    public void removeBasic(Long id);

    public Basic getBasic(Long id);

    public List<Basic> listBasics();

    public void setComplex(Long idE, Long idR,Date date);

    public List<Employees>  returnEmployeeByRecord(Long firstIdRecord,Long lastIdRecord, boolean status);

    public void addEmployeeToBasic(Employees employees, Date date, boolean status);

    public int returnEmployeeFalse(Employees employee);

    public void setStatus(Long id, boolean status);

}
