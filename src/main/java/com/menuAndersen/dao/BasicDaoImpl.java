package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.Employees;
import com.menuAndersen.model.MyDate;
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
    public void setComplex(Long idE, Long idR,Date date) {
        String sql = "update Basic b set idRecord =" + idR + " where idEmployee = " + idE+
                " and b.idRecord = ANY(select idRecord from DateAndComplexes where " +
                " idDate = (select idDate from MyDate where date=:date))";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("date",date);
        query.executeUpdate();

    }

    @Override
    public List<Employees> returnEmployeeByRecord(Long firstIdRecord,Long lastIdRecord, boolean status) {
        String sql = "select idEmployee from Basic b where (idRecord between :par1 and :par2)   AND  (b.status = "+ status+"))";
       // String sql = "select idEmployee from Basic where idRecord = " + idRecord+ "  AND :par  = ANY( from Employees where status = "+ status+")";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("par1",firstIdRecord);
        query.setParameter("par2",lastIdRecord);
        List<Employees> list = query.list();

        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public int returnEmployeeFalse(Employees employee) {
        String sql = "from Basic  where status = false and idEmployee = " + employee.getIdEmployee();
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        List list = query.list();
        return list.size();
    }


    @Override
    public void addEmployeeToBasic(Employees employees, Date date, boolean status) {
        String sql = "insert into Basic (idEmployee) from Employees where idEmployee ="+employees.getIdEmployee();
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();
        String sql1 = "update Basic set status ="+ status+", idRecord = (" +
                "from DateAndComplexes where " +
                "idComplex = ANY(from Complexes where number=0) " +
                "and idDate = (from MyDate where date =:date))" +
                "where idEmployee="+employees.getIdEmployee()+" and idRecord=null";
        Query query1 = this.sessionFactory.getCurrentSession().createQuery(sql1);
        query1.setParameter("date",date);
        query1.executeUpdate();
    }
    @Override
    public void setStatus(Long id, boolean status) {
        String sql = "update Basic set status =" + status + " where idEmployee = " + id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        int rez = query.executeUpdate();

    }
}