package com.menuAndersen.dao;

import com.menuAndersen.model.MyDate;

import java.util.List;

public interface MyDateDao {
    public void addDate(MyDate myDate);

    public void editDate(MyDate myDate);

    public void removeDate(Long id);

    public MyDate getDate(Long id);

    public List<MyDate> listDate();
}
