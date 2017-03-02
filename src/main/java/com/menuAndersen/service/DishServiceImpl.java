package com.menuAndersen.service;
import com.menuAndersen.dao.DishDao;
import com.menuAndersen.model.Dish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("dishService")
public class DishServiceImpl implements DishService{

    private DishDao dishDao;
    public DishServiceImpl(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    @Transactional
    public void addDish(Dish dish) {

            this.dishDao.addDish(dish);
    }

    @Override
    @Transactional
    public List<Dish> returnDishByType(String typeDish) {
        return this.dishDao.returnDishByType(typeDish);
    }
}
