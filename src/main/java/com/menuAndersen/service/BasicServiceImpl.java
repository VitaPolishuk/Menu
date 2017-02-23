package com.menuAndersen.service;

import com.menuAndersen.dao.BasicDao;
import com.menuAndersen.model.Basic;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.Employees;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service("basicService")
public class BasicServiceImpl implements BasicService {
    private BasicDao basicDao;

    public BasicServiceImpl(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    @Override
    @Transactional
    public void addBasic(Basic basic) {
        this.basicDao.addBasic(basic);
    }

    @Override
    @Transactional
    public void ediBasic(Basic basic) {
        this.basicDao.ediBasic(basic);
    }

    @Override
    @Transactional
    public void removeBasic(Long id) {
        this.basicDao.removeBasic(id);
    }

    @Override
    @Transactional
    public Basic getBasic(Long id) {
        return this.basicDao.getBasic(id);
    }

    @Override
    @Transactional
    public List<Basic> listBasics() {
        return this.basicDao.listBasics();
    }

    @Override
    @Transactional
    public void setComplex(Long idE, Long idR,Date date) {
       this.basicDao.setComplex(idE,idR,date);

    }
    @Override
    @Transactional
    public List<Employees>  returnEmployeeByRecord(Long firstIdRecord,Long lastIdRecord, boolean status) {
        return  this.basicDao.returnEmployeeByRecord(firstIdRecord,lastIdRecord, status);
    }

    @Override
    @Transactional
    public void addEmployeeToBasic(Employees employees, Date date, boolean status)  {
        this.basicDao.addEmployeeToBasic(employees,date,status);
    }
    @Override
    @Transactional
    public int returnEmployeeFalse(Employees employee){
        return this.basicDao.returnEmployeeFalse(employee);
    }
    @Override
    @Transactional
    public void setStatus(Long id, boolean status){
        this.basicDao.setStatus(id,status);
    }
}
