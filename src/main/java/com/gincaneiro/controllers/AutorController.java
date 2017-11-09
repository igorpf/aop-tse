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

import com.gincaneiro.entities.Autor;
import com.gincaneiro.services.AutorService;
import com.gincaneiro.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author igorpiresferreira
 */
@Controller
@RequestMapping("/autor")
public class AutorController extends GenericCrudController<Autor>{

    @Autowired
    AutorService service;
    
    @Override
    public GenericService<Autor> getService() {
        return service;
    }
    
}
