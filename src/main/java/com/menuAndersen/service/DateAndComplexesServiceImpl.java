package com.menuAndersen.service;

import com.menuAndersen.dao.DateAndComplexesDao;
import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.Dish;
import com.menuAndersen.model.MyDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service("dateAndComplexesService")
public class DateAndComplexesServiceImpl implements DateAndComplexesService{
    private DateAndComplexesDao dateAndComplexesDao;

    public DateAndComplexesServiceImpl(DateAndComplexesDao dateAndComplexesDao) {
        this.dateAndComplexesDao = dateAndComplexesDao;
    }

    @Override
    @Transactional
    public void addDateComplex(DateAndComplexes dateAndComplexes) {
        this.dateAndComplexesDao.addDateComplex(dateAndComplexes);
    }

    @Override
    @Transactional
    public void editDateComplex(DateAndComplexes dateAndComplexes) {
        this.dateAndComplexesDao.editDateComplex(dateAndComplexes);
    }

    @Override
    @Transactional
    public void removeDateComplex(Long id) {
        this.dateAndComplexesDao.removeDateComplex(id);
    }

    @Override
    @Transactional
    public DateAndComplexes getDateComplex(Long id) {
        return this.dateAndComplexesDao.getDateComplex(id);
    }

    @Override
    @Transactional
    public List<DateAndComplexes> listDateComplexes() {
        return this.dateAndComplexesDao.listDateComplexes();
    }

    @Override
    @Transactional
    public List<Long> returnIdRecordByDate(Date date) {
        return this.dateAndComplexesDao.returnIdRecordByDate(date);
    }

    @Override
    @Transactional
    public List<Complexes> returnIdComplexesByDate(Date date) {
        return this.dateAndComplexesDao.returnIdComplexesByDate(date);
    }
    @Override
    @Transactional
    public void addToDate(Complexes complexes,MyDate myDate){
         this.dateAndComplexesDao.addToDate(complexes,myDate);
    }

    @Override
    @Transactional
    public List<Dish> returnDishByDate(Date date) {
        return this.dateAndComplexesDao.returnDishByDate(date);
    }

}
