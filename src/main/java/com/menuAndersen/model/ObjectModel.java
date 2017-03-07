package com.menuAndersen.model;

import java.util.List;

public class ObjectModel {

    private List<Employees> employeesList;
    private List<Integer> numberList;
    private List<Dish> dishList;

    private DishListAll DishListAll;

    private List<Long> idRecordList;
    private MyDate myDate;
    private TimeBlocked timeBlocked;


    public DishListAll getDishListAll() {
        return DishListAll;
    }

    public void setDishListAll(DishListAll DishListAll) {
        this.DishListAll = DishListAll;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public TimeBlocked getTimeBlocked() {
        return timeBlocked;
    }

    public void setTimeBlocked(TimeBlocked timeBlocked) {
        this.timeBlocked = timeBlocked;
    }

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


    public List<Long> getIdRecordList() {
        return idRecordList;
    }

    public void setIdRecordList(List<Long> idRecordList) {
        this.idRecordList = idRecordList;
    }
}
