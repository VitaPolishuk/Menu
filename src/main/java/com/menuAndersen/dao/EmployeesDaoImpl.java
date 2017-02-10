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
        Session session = this.sessionFactory.getCurrentSession();
        Employees employee = (Employees) session.load(Employees.class, new Long(id));

        return employee;
    }

    @Override
    public List<Employees> listEmployees() {
        @SuppressWarnings("unchecked")
        List<Employees> listEmployees = (List<Employees>) sessionFactory.getCurrentSession()
                .createCriteria(Employees.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listEmployees;
    }
}
