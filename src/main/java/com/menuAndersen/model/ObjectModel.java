package com.menuAndersen.model;

import java.sql.Date;
import java.util.List;

public class ObjectModel {

    private List<Employees> employeesList;
    private List<Integer> numberList;
    private List<Complexes> complexesList;
    private List<Long> idRecordList;
    private MyDate myDate;

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }

    public List<Complexes> getComplexesList() {
        return complexesList;
    }

    public void setComplexesList(List<Complexes> complexesList) {
        this.complexesList = complexesList;
    }

    public List<Long> getIdRecordList() {
        return idRecordList;
    }

    public void setIdRecordList(List<Long> idRecordList) {
        this.idRecordList = idRecordList;
    }
}
