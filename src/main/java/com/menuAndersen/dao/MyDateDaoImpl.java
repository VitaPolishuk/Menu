package com.menuAndersen.dao;

import com.menuAndersen.model.MyDate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("myDateDao")
public class MyDateDaoImpl implements MyDateDao{
    private SessionFactory sessionFactory;

    public MyDateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDate(MyDate myDate) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(myDate);
    }

    @Override
    public void editDate(MyDate myDate) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(myDate);
    }

    @Override
    public void removeDate(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        MyDate myDate = (MyDate) session.load(MyDate.class, new Long(id));
        if (myDate != null) {
            session.delete(myDate);
        }
    }

    @Override
    public MyDate getDateById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        MyDate myDate = (MyDate) session.load(MyDate.class, new Long(id));

        return myDate;
    }

    @Override
    public List<MyDate> listDate() {
        @SuppressWarnings("unchecked")
        List<MyDate> listMyDate = (List<MyDate>) sessionFactory.getCurrentSession()
                .createCriteria(MyDate.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listMyDate;
    }

    @Override
    public MyDate getDateByValue(Date myDate) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("from MyDate where date = :dateValue");
        query.setParameter("dateValue", myDate);
        List<MyDate> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void setStatusDate(Date date) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("update MyDate set blocked=false where date = :dateValue");
        query.setParameter("dateValue", date);
        query.executeUpdate();
    }
}
