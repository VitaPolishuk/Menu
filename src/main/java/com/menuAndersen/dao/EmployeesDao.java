package com.menuAndersen.dao;

import com.menuAndersen.model.Employees;

import java.util.List;

public interface EmployeesDao {
    public void addEmployees(Employees employees);

    public void editEmployees(Employees employees);

    public void removeEmployees(long id);

    public Employees getEmployees(long id);

    public List<Employees> listEmployees();

}
