package com.menuAndersen.service;

import com.menuAndersen.dao.PasswordDao;
import com.menuAndersen.model.Password;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {
    private PasswordDao passwordDao;

    public PasswordServiceImpl(PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }

    @Override
    @Transactional
    public void addPassword(String passwordHash, String currentTime, String globalTime) {
        this.passwordDao.addPassword(passwordHash,currentTime,globalTime);
    }

    @Override
    @Transactional
    public void editPassword(Password password) {
        this.passwordDao.editPassword(password);
    }

    @Override
    @Transactional
    public void removePassword(Long id) {
        this.passwordDao.removePassword(id);
    }

    @Override
    @Transactional
    public Password getPassword(Long id){
        return this.passwordDao.getPassword(id);
    }
}
