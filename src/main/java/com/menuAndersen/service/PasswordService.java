package com.menuAndersen.service;

import com.menuAndersen.model.Password;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PasswordService {
    public void addPassword(String passwordHash);

    public void editPassword(Password password);

    public void removePassword(Long id);

    public Password getPassword(Long id);
}
