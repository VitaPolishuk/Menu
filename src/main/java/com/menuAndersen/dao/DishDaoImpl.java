package com.menuAndersen.dao;

import com.menuAndersen.model.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("dishDao")
public class DishDaoImpl implements DishDao{

    private SessionFactory sessionFactory;
    public DishDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDish(Dish dish) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(dish);
    }

    @Override
    public List<Dish> returnDishByType(String typeDish) {
        String sql = "from Dish  where typeDish  =:par";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("par", typeDish);
        List<Dish> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
    }
}
