package com.menuAndersen.service;

import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.MyDate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface DateAndComplexesService {
    public void addDateComplex(DateAndComplexes dateAndComplexes);

    public void editDateComplex(DateAndComplexes dateAndComplexes);

    public void removeDateComplex(Long id);

    public DateAndComplexes getDateComplex(Long id);

    public List<DateAndComplexes> listDateComplexes();

    public List<Long> returnIdRecordByDate(Date date);

    public List<Complexes>  returnIdComplexesByDate(Date date);

    public void addToDate(Complexes complexes,MyDate myDate);
}

