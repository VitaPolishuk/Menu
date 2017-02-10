package com.menuAndersen.service;

import com.menuAndersen.dao.EmployeesDao;
import com.menuAndersen.model.Employees;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService {
    private EmployeesDao employeesDao;


    public EmployeesServiceImpl(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }
    @Override
    @Transactional
    public void addEmployees(Employees employees) {
       this.employeesDao.addEmployees(employees);
    }

    @Override
    @Transactional
    public void editEmployees(Employees employees) {
        this.employeesDao.editEmployees(employees);
    }

    @Override
    @Transactional
    public void removeEmployees(long id) {
        this.employeesDao.removeEmployees(id);
    }

    @Override
    @Transactional
    public Employees getEmployees(long id) {
        return this.employeesDao.getEmployees(id);
    }

    @Override
    @Transactional
    public List<Employees> listEmployees() {
        return  this.employeesDao.listEmployees();
    }
}
