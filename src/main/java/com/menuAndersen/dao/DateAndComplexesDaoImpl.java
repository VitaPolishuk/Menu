package com.menuAndersen.dao;

import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.MyDate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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

    @Override
    public List<Long> returnIdRecordByDate(Date date) {
       String sql = "select idRecord from DateAndComplexes where idDate = ( from MyDate where date = :parDate)";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("parDate", date);
        List<Long> list = query.list();
        return list;
    }

    @Override
    public List<Complexes> returnIdComplexesByDate(Date date) {
        String sql = "select idComplex from DateAndComplexes where idDate = ( from MyDate where date = :parDate)";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("parDate", date);
        List<Complexes> list = query.list();
        return list;
    }


    @Override
    public void addToDate(Complexes complexes,MyDate myDate) {
        String sql = "insert into DateAndComplexes (idComplex,idDate) " +
                "from Complexes c,MyDate m where c.idComplex ="+complexes.getIdComplex()+
                " and m.idDate="+myDate.getIdDate();
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();

    }


}
