package com.menuAndersen.service;

import com.menuAndersen.dao.ComplexesDao;
import com.menuAndersen.model.Complexes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("complexesService")
public class ComplexesServiceImpl implements ComplexesService {
    private ComplexesDao complexesDao;

    public ComplexesServiceImpl(ComplexesDao complexesDao) {
        this.complexesDao = complexesDao;
    }

    @Override
    @Transactional
    public void addComplex(Complexes complex) {
        this.complexesDao.addComplex(complex);
    }

    @Override
    @Transactional
    public void editComplex(Complexes complex) {
        this.complexesDao.editComplex(complex);
    }

    @Override
    @Transactional
    public void removeComplex(Long id) {
        this.complexesDao.removeComplex(id);
    }

    @Override
    @Transactional
    public Complexes getComplex(Long id) {

        return this.complexesDao.getComplex(id);
    }

    @Override
    @Transactional
    public List<Complexes> listComplexes() {
        return this.complexesDao.listComplexes();
    }
}
