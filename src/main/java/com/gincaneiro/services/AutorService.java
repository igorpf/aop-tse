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

import com.gincaneiro.entities.Autor;
import com.gincaneiro.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author igorpiresferreira
 */
@Service
public class AutorService extends GenericService<Autor>{

    @Autowired
    private AutorRepository repo;
    
    @Override
    public JpaRepository<Autor, Long> getRepository() {
        return repo;
    }
    
}
