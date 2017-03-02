package com.menuAndersen.dao;

import com.menuAndersen.model.TimeBlocked;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wital on 01.03.2017.
 */
@Repository("timeblockedDao")
public class TimeBlockedDaoImpl implements TimeBlockedDao {
    private SessionFactory sessionFactory;

    public TimeBlockedDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTime(String currentTime, String globalTime) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("insert into TimeBlocked (currentTime,globalTime) values('" + currentTime + "','"+globalTime+"')");
        query.executeUpdate();
    }

    @Override
    public void editTime(TimeBlocked timeBlocked) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(timeBlocked);
    }

    @Override
    public void removeTime(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        TimeBlocked timeBlocked = (TimeBlocked) session.load(TimeBlocked.class, new Long(id));
        if (timeBlocked != null) {
            session.delete(timeBlocked);
        }
    }

    @Override
    public TimeBlocked getTime(Long id) {
        String str = "from TimeBlocked where idTime=" + id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(str);
        @SuppressWarnings("unchecked")
        List<TimeBlocked> list = (List<TimeBlocked>) query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void cheangeCurrentTime(String currentTime) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("update TimeBlocked set currentTime = '"+currentTime+"' where idTime=1");
        query.executeUpdate();
    }

    @Override
    public void cheangeGlobalTime(String globalTime) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("update TimeBlocked set globalTime = '"+globalTime+"' where idTime=1");
        query.executeUpdate();
    }
}

