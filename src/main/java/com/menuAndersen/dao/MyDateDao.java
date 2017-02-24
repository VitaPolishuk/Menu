package com.menuAndersen.dao;

import com.menuAndersen.model.MyDate;

import java.sql.Date;
import java.util.List;

public interface MyDateDao {
    public void addDate(MyDate myDate);

    public void editDate(MyDate myDate);

    public void removeDate(Long id);

    public MyDate getDateById(Long id);

    public List<MyDate> listDate();

    public MyDate getDateByValue(Date myDate);

    public void setStatusDate(Date date);

    public void setAllStatusFalse();
}
