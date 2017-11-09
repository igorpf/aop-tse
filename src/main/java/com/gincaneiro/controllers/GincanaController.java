/*
 * Copyright (c) 2016 Igor Pires Ferreira
 * ipferreira@inf.ufrgs.br
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 * In no event shall the author be liable for any claim or damages.
 *
 * Todos os direitos reservados.
 */
package com.gincaneiro.controllers;

import com.gincaneiro.entities.Gincana;
import com.gincaneiro.services.GenericService;
import com.gincaneiro.services.GincanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author igorpiresferreira
 */
@Controller
@RequestMapping("/gincana")
public class GincanaController extends GenericCrudController<Gincana>{
    
    @Autowired
    private GincanaService service;

    @Override
    public GenericService<Gincana> getService() {
        return service;
    }
}
