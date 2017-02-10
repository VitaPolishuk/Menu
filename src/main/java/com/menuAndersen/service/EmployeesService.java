package com.menuAndersen.service;

import com.menuAndersen.model.Employees;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeesService {

    public void addEmployees(Employees employees);

    public void editEmployees(Employees employees);

    public void removeEmployees(long id);

    public Employees getEmployees(long id);

    public List<Employees> listEmployees();
}
