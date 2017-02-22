package com.menuAndersen.dao;

import com.menuAndersen.model.Basic;
import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.Employees;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("complexesDao")
public class ComplexesDaoImpl implements ComplexesDao {
    private SessionFactory sessionFactory;

    public ComplexesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addComplex(Complexes complex) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(complex);
    }

    @Override
    public void editComplex(Complexes complex) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(complex);
    }

    @Override
    public void removeComplex(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Complexes complex = (Complexes) session.load(Complexes.class, new Long(id));
        if (complex != null) {
            session.delete(complex);
        }
    }

    @Override
    public Complexes getComplex(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Complexes complex = (Complexes) session.load(Complexes.class, new Long(id));
        return complex;
    }

    @Override
    public List<Complexes> listComplexes() {
        @SuppressWarnings("unchecked")
        List<Complexes> listComplexes = (List<Complexes>) sessionFactory.getCurrentSession()
                .createCriteria(Complexes.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listComplexes;
    }


    @Override
    public int numberToEmployee(Employees employees, Long idR) {
        System.out.println("11111111");
       String sql = "select b from Basic b " +
               "inner join b.idRecord d " +
               "inner join d.idComplex c " +
               "inner join b.idEmployee e " +
               "where d.idRecord="+idR+" and e.idEmployee="+employees.getIdEmployee();
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);

        @SuppressWarnings("unchecked")
        List<Basic> list =  query.list();

        if (list != null && !list.isEmpty()) {
            return list.get(0).getIdRecord().getIdComplex().getNumber();
        }
        return 5;
    }

}
