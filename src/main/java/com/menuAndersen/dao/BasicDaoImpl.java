package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.Employees;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("basicDao")
public class BasicDaoImpl implements BasicDao{
    private SessionFactory sessionFactory;

    public BasicDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addBasic(Basic basic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(basic);
    }

    @Override
    public void ediBasic(Basic basic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(basic);
    }

    @Override
    public void removeBasic(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Basic basic = (Basic) session.load(Basic.class, new Long(id));
        if (basic != null) {
            session.delete(basic);
        }
    }

    @Override
    public Basic getBasic(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Basic basic = (Basic) session.load(Basic.class, new Long(id));

        return basic;
    }

    @Override
    public List<Basic> listBasics() {
        @SuppressWarnings("unchecked")
        List<Basic> listBasics = (List<Basic>) sessionFactory.getCurrentSession()
                .createCriteria(Basic.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listBasics;
    }
}
