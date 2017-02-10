package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;

import java.util.List;

public interface BasicDao {
    public void addBasic(Basic basic);

    public void ediBasic(Basic basic);

    public void removeBasic(Long id);

    public Basic getBasic(Long id);

    public List<Basic> listBasics();
}
