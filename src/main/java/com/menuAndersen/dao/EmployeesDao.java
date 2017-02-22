package com.menuAndersen.dao;

import com.menuAndersen.model.Employees;

import java.util.List;

public interface EmployeesDao {
    public void addEmployee(Employees employee);

    public void editEmployee(Employees employee);

    public void removeEmployee(Long id);

    public Employees getEmployee(Long id);

    public List<Employees> listEmployees();

    public void setStatus(Long id, boolean status);

    public List<Employees> listEmployeesToStatus(boolean status);
}
