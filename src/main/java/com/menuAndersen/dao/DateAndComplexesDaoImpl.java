package com.menuAndersen.dao;

import com.menuAndersen.model.DateAndComplexes;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dateAndComplexesDao")
public class DateAndComplexesDaoImpl implements DateAndComplexesDao {
    private SessionFactory sessionFactory;

    public DateAndComplexesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addDateComplex(DateAndComplexes dateAndComplexes) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(dateAndComplexes);
    }

    @Override
    public void editDateComplex(DateAndComplexes dateAndComplexes) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(dateAndComplexes);
    }

    @Override
    public void removeDateComplex(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        DateAndComplexes dateAndComplexes = (DateAndComplexes) session.load(DateAndComplexes.class, new Long(id));
        if (dateAndComplexes != null) {
            session.delete(dateAndComplexes);
        }
    }

    @Override
    public DateAndComplexes getDateComplex(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        DateAndComplexes dateAndComplexes = (DateAndComplexes) session.load(DateAndComplexes.class, new Long(id));
        return dateAndComplexes;
    }

    @Override
    public List<DateAndComplexes> listDateComplexes() {
        @SuppressWarnings("unchecked")
        List<DateAndComplexes> listDateComplexes = (List<DateAndComplexes>) sessionFactory.getCurrentSession()
                .createCriteria(DateAndComplexes.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listDateComplexes;
    }
}
