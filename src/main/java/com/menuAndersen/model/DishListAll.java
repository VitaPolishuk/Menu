package com.menuAndersen.model;

import java.util.List;

/**
 * Created by wital on 07.03.2017.
 */
public class DishListAll {

    private List<Dish> dishListFirst;
    private List<Dish> dishListGarnor;
    private List<Dish> dishListMeat;
    private List<Dish> dishListSalat;
    private List<Dish> dishListDrink;

    public DishListAll() {
    }

    public DishListAll(List<Dish> dishListFirst, List<Dish> dishListGarnor, List<Dish> dishListMeat, List<Dish> dishListSalat, List<Dish> dishListDrink) {
        this.dishListFirst = dishListFirst;
        this.dishListGarnor = dishListGarnor;
        this.dishListMeat = dishListMeat;
        this.dishListSalat = dishListSalat;
        this.dishListDrink = dishListDrink;
    }

    public List<Dish> getDishListFirst() {
        return dishListFirst;
    }

    public void setDishListFirst(List<Dish> dishListFirst) {
        this.dishListFirst = dishListFirst;
    }

    public List<Dish> getDishListGarnor() {
        return dishListGarnor;
    }

    public void setDishListGarnor(List<Dish> dishListGarnor) {
        this.dishListGarnor = dishListGarnor;
    }

    public List<Dish> getDishListMeat() {
        return dishListMeat;
    }

    public void setDishListMeat(List<Dish> dishListMeat) {
        this.dishListMeat = dishListMeat;
    }

    public List<Dish> getDishListSalat() {
        return dishListSalat;
    }

    public void setDishListSalat(List<Dish> dishListSalat) {
        this.dishListSalat = dishListSalat;
    }

    public List<Dish> getDishListDrink() {
        return dishListDrink;
    }

    public void setDishListDrink(List<Dish> dishListDrink) {
        this.dishListDrink = dishListDrink;
    }
}
