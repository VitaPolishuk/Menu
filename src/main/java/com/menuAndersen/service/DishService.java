package com.menuAndersen.service;

import com.menuAndersen.model.Dish;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DishService {

    public void addDish(Dish dish);

    public List<Dish> returnDishByType(String typeDish);
    public List<Dish> listDish();
    public void editDish(Long id,String name,String type);
    public void editDishImg(Long id, byte[] img);
    public Dish returnDish();
    public Dish getDish(Long id);
}
