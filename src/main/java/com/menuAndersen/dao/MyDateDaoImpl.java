package com.menuAndersen.dao;

import com.menuAndersen.model.MyDate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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
    public MyDate getDate(Long id) {
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
}
