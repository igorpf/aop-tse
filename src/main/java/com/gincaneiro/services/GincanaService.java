/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gincaneiro.services;

import com.gincaneiro.entities.Gincana;
import com.gincaneiro.repositories.GincanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author igorpiresferreira
 */
@Service
public class GincanaService extends GenericService<Gincana> {

    @Autowired
    private GincanaRepository repo;
    
    @Override
    public JpaRepository<Gincana, Long> getRepository() {
        return repo;
    }

}
