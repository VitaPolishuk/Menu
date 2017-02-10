package com.menuAndersen.dao;

import com.menuAndersen.model.Employees;
import org.hibernate.Criteria;
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
    public void addEmployees(Employees employees) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(employees);
    }

    @Override
    public void editEmployees(Employees employees) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employees);

    }

    @Override
    public void removeEmployees(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employees employees = (Employees) session.load(Employees.class, new Long(id));
        if (employees != null) {
            session.delete(employees);
        }
    }

    @Override
    public Employees getEmployees(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employees employees = (Employees) session.load(Employees.class, new Long(id));

        return employees;
    }

    @Override
    public List<Employees> listEmployees() {
        @SuppressWarnings("unchecked")
        List<Employees> listEmpl = (List<Employees>) sessionFactory.getCurrentSession()
                .createCriteria(Employees.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listEmpl;
    }
}
