package com.menuAndersen.dao;

import com.menuAndersen.model.Dish;
import org.hibernate.Criteria;
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

    @Override
    public List<Dish> listDish() {
        @SuppressWarnings("unchecked")
        List<Dish> listDish = (List<Dish>) sessionFactory.getCurrentSession()
                .createCriteria(Dish.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listDish;
    }

    @Override
    public void editDish(Long id,String name,String type) {
        String sql = " update Dish set nameDish='"+name+"', typeDish='"+type+"' where idDish="+id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();

    }

    @Override
    public Dish returnDish() {
        String sql = " from Dish order by idDish desc";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);

        List<Dish> list = query.setMaxResults(1).list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Dish getDish(Long id) {
        String sql = " from Dish where idDish ="+id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        List<Dish> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
