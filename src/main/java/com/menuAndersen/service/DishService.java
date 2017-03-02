package com.menuAndersen.service;

import com.menuAndersen.model.Dish;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DishService {

    public void addDish(Dish dish);

    public List<Dish> returnDishByType(String typeDish);
    public List<Dish> listDish();
}
