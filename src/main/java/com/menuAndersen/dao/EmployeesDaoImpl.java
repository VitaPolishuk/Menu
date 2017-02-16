package com.menuAndersen.dao;

import com.menuAndersen.model.Employees;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeesDao")
public class EmployeesDaoImpl implements EmployeesDao {
    private SessionFactory sessionFactory;

    public EmployeesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmployee(Employees employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(employee);
    }

    @Override
    public void editEmployee(Employees employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employee);

    }

    @Override
    public void removeEmployee(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employees employee = (Employees) session.load(Employees.class, new Long(id));
        if (employee != null) {
            session.delete(employee);
        }
    }

    @Override
    public Employees getEmployee(Long id) {
        String str = "from Employees where idEmployee="+id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(str);
      //  Session session = this.sessionFactory.getCurrentSession();
      //  Employees employee = (Employees) session.load(Employees.class, new Long(id));
        @SuppressWarnings("unchecked")
        List<Employees> list = (List<Employees>) query.list();

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Employees> listEmployees() {
        @SuppressWarnings("unchecked")
        List<Employees> listEmployees = (List<Employees>) sessionFactory.getCurrentSession()
                .createCriteria(Employees.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listEmployees;
    }

    @Override
    public void setStatus(Long id, boolean status) {
        String sql = "update Employees set status =" + status + " where idEmployee = " + id;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        int rez = query.executeUpdate();

    }
}
