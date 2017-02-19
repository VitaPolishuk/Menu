package com.menuAndersen.service;

import com.menuAndersen.model.Basic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BasicService {
    public void addBasic(Basic basic);

    public void ediBasic(Basic basic);

    public void removeBasic(Long id);

    public Basic getBasic(Long id);

    public List<Basic> listBasics();

    public void setComplex(Long idE, Long idR);
}
