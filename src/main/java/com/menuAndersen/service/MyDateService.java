package com.menuAndersen.service;

import com.menuAndersen.model.MyDate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MyDateService {
    public void addDate(MyDate myDate);

    public void editDate(MyDate myDate);

    public void removeDate(Long id);

    public MyDate getDate(Long id);

    public List<MyDate> listDate();
}
