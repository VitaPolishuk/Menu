package com.menuAndersen.dao;


import com.menuAndersen.model.Dish;

import java.util.List;

public interface DishDao {

    public void addDish(Dish dish);

    public List<Dish> returnDishByType(String typeDish);

    public List<Dish> listDish();

    public void editDish(Long id,String name,String type);

    public Dish returnDish();

    public Dish getDish(Long id);
}
