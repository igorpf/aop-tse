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

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author igorpiresferreira
 */
public abstract class GenericService <T>{
    
    protected abstract JpaRepository<T,Long> getRepository();
    
    public void edit(T g){
        getRepository().save(g);
    }

    public List<T> getAll(){
        return getRepository().findAll();
    }

    public T get(Long id){
        return getRepository().findOne(id);
    }

    public void save(T g){
        getRepository().save(g);
    }

    public void delete(Long id){
        getRepository().delete(id);
    }
    public void delete(T g){
        getRepository().delete(g);
    }
}
