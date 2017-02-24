package com.menuAndersen.service;

import com.menuAndersen.dao.MyDateDao;
import com.menuAndersen.model.MyDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service("myDateService")
public class MyDateServiceImpl implements MyDateService {
    private MyDateDao myDateDao;

    public MyDateServiceImpl(MyDateDao myDateDao) {
        this.myDateDao = myDateDao;
    }

    @Override
    @Transactional
    public void addDate(MyDate myDate) {
        this.myDateDao.addDate(myDate);
    }

    @Override
    @Transactional
    public void editDate(MyDate myDate) {
        this.myDateDao.editDate(myDate);
    }

    @Override
    @Transactional
    public void removeDate(Long id) {
        this.myDateDao.removeDate(id);
    }

    @Override
    @Transactional
    public MyDate getDateById(Long id) {
        return this.myDateDao.getDateById(id);
    }

    @Override
    @Transactional
    public List<MyDate> listDate() {
        return this.myDateDao.listDate();
    }

    @Override
    @Transactional
    public MyDate getDateByValue(Date myDate) {
        return this.myDateDao.getDateByValue(myDate);
    }

    @Override
    @Transactional
    public void setStatusDate(Date date) {
        this.myDateDao.setStatusDate(date);
    }
    @Override
    @Transactional
    public void setAllStatusFalse(){
        this.myDateDao.setAllStatusFalse();
    }
}
