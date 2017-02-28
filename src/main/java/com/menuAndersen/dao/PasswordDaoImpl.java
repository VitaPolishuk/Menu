package com.menuAndersen.dao;

import com.menuAndersen.model.Password;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("passwordDao")
public class PasswordDaoImpl implements PasswordDao {
    private SessionFactory sessionFactory;

    public PasswordDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPassword(String passwordHash, String currentTime, String globalTime) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("insert into Password (password,currentTime,globalTime) values(" + passwordHash + ",currentTime,globalTime)");
        query.executeUpdate();
    }

    @Override
    public void editPassword(Password password) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(password);
    }

    @Override
    public void removePassword(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Password password = (Password) session.load(Password.class, new Long(id));
        if (password != null) {
            session.delete(password);
        }
    }

    @Override
    public Password getPassword(Long id) {
        String str = "from Password where idPassword=" + id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(str);
        @SuppressWarnings("unchecked")
        List<Password> list = (List<Password>) query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
