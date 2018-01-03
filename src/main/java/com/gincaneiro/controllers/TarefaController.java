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

import com.gincaneiro.entities.Tarefa;
import com.gincaneiro.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 *
 * @author igorpiresferreira
 */
@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Secured("ROLE_PAYING")
    @RequestMapping(value = "/{gid}/{tid}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Tarefa> get(@PathVariable("gid") Long gincanaId,
            @PathVariable("tid") Long tarefaId) {
        Tarefa t = service.get(gincanaId, tarefaId);

        if (t == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @Secured("ROLE_PAYING")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody Tarefa t, BindingResult result) {
        try {
            if (result.hasErrors() ) {
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            service.saveTarefa(t);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
