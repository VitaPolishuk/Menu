package com.menuAndersen.dao;

import com.menuAndersen.model.DateAndComplexes;

import java.util.List;

public interface DateAndComplexesDao {
    public void addDateComplex(DateAndComplexes dateAndComplexes);

    public void editDateComplex(DateAndComplexes dateAndComplexes);

    public void removeDateComplex(Long id);

    public DateAndComplexes getDateComplex(Long id);

    public List<DateAndComplexes> listDateComplexes();
}
