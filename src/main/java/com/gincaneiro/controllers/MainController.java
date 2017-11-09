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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author igorpiresferreira
 */
@Controller
public class MainController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

    @RequestMapping(value="/admin",method = RequestMethod.GET)
    public String admin(){
        return "components/admin/index";
    }
}
