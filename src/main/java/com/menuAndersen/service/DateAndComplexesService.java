package com.menuAndersen.service;

import com.menuAndersen.model.DateAndComplexes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DateAndComplexesService {
    public void addDateComplex(DateAndComplexes dateAndComplexes);

    public void editDateComplex(DateAndComplexes dateAndComplexes);

    public void removeDateComplex(Long id);

    public DateAndComplexes getDateComplex(Long id);

    public List<DateAndComplexes> listDateComplexes();
}

