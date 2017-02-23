package com.menuAndersen.dao;

import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.Employees;

import java.sql.Date;
import java.util.List;

public interface ComplexesDao {

    public void addComplex(Complexes complex);

    public void editComplex(Complexes complex);

    public void removeComplex(Long id);

    public Complexes getComplex(Long id);

    public List<Complexes> listComplexes();

    public int numberToEmployee(Employees employees,Long idR);

    public List<Complexes> returnComplexesByDate(Date date);
}
