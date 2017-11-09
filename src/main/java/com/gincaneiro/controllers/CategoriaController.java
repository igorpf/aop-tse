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

import com.gincaneiro.entities.Categoria;
import com.gincaneiro.services.CategoriaService;
import com.gincaneiro.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author igorpiresferreira
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController extends GenericCrudController<Categoria>{

    @Autowired
    private CategoriaService service;
    
    @Override
    public GenericService<Categoria> getService() {
        return service;
    }
    
}
