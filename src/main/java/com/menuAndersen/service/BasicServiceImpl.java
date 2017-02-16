package com.menuAndersen.service;

import com.menuAndersen.dao.BasicDao;
import com.menuAndersen.model.Basic;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("basicService")
public class BasicServiceImpl implements BasicService {
    private BasicDao basicDao;

    public BasicServiceImpl(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    @Override
    @Transactional
    public void addBasic(Basic basic) {
        this.basicDao.addBasic(basic);
    }

    @Override
    @Transactional
    public void ediBasic(Basic basic) {
        this.basicDao.ediBasic(basic);
    }

    @Override
    @Transactional
    public void removeBasic(Long id) {
        this.basicDao.removeBasic(id);
    }

    @Override
    @Transactional
    public Basic getBasic(Long id) {
        return this.basicDao.getBasic(id);
    }

    @Override
    @Transactional
    public List<Basic> listBasics() {
        return this.basicDao.listBasics();
    }

    @Override
    @Transactional
    public void setComplex(Long idE, Long idR) {
       this.basicDao.setComplex(idE,idR);

    }
}
