package com.menuAndersen.service;

import com.menuAndersen.model.Complexes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ComplexesService {
    public void addComplex(Complexes complex);

    public void editComplex(Complexes complex);

    public void removeComplex(Long id);

    public Complexes getComplex(Long id);

    public List<Complexes> listComplexes();
}
