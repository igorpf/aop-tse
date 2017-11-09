/*
 * Copyright (c) 2016 Igor Pires Ferreira
 * ipferreira@inf.ufrgs.br
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 * In no event shall the author be liable for any claim or damages.
 *
 * Todos os direitos reservados.
 */
package com.gincaneiro.services;

import com.gincaneiro.entities.Categoria;
import com.gincaneiro.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author igorpiresferreira
 */
@Service
public class CategoriaService extends GenericService<Categoria>{

    @Autowired
    private CategoriaRepository repo;
    
    @Override
    public JpaRepository<Categoria, Long> getRepository() {
        return repo;
    }
    
}
