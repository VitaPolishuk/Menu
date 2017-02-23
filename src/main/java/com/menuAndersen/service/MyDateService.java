package com.menuAndersen.service;

import com.menuAndersen.model.MyDate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface MyDateService {
    public void addDate(MyDate myDate);

    public void editDate(MyDate myDate);

    public void removeDate(Long id);

    public MyDate getDateById(Long id);

    public List<MyDate> listDate();

    public MyDate getDateByValue(Date myDate);
    public void setStatusDate(Date date);
}
