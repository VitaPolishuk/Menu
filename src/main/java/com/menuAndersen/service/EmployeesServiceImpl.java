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
        this.employeesDao.addEmployee(employees);
    }

    @Override
    @Transactional
    public void editEmployees(Employees employees) {
        this.employeesDao.editEmployee(employees);
    }

    @Override
    @Transactional
    public void removeEmployees(long id) {
        this.employeesDao.removeEmployee(id);
    }

    @Override
    @Transactional
    public Employees getEmployee(long id) {
        return this.employeesDao.getEmployee(id);
    }

    @Override
    @Transactional
    public List<Employees> listEmployees() {
        return this.employeesDao.listEmployees();
    }
    @Override
    @Transactional
    public void setStatus(Long id, boolean status){
         this.employeesDao.setStatus(id,status);
    }

}
