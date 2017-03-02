package com.menuAndersen.dao;


import com.menuAndersen.model.Password;

public interface PasswordDao {
    public void addPassword(String passwordHash);

    public void editPassword(Password password);

    public void removePassword(Long id);

    public Password getPassword(Long id);
}
