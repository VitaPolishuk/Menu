package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.Employees;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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

    @Override
    public void setComplex(Long idE, Long idR) {
        String sql = "update Basic set idRecord =" + idR + " where idEmployee = " + idE;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();

    }


    @Override
    public void addEmployeeToBasic(Employees employees, Date date) {
        String sql = "insert into Basic (idEmployee) from Employees where idEmployee ="+employees.getIdEmployee();
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();
        String sql1 = "update Basic set  idRecord = (" +
                "from DateAndComplexes where " +
                "idComplex = ANY(from Complexes where number=0) " +
                "and idDate = (from MyDate where date =:date))" +
                "where idEmployee="+employees.getIdEmployee();
        Query query1 = this.sessionFactory.getCurrentSession().createQuery(sql1);
        query1.setParameter("date",date);
        query1.executeUpdate();
    }
}