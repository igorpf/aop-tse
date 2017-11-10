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

import com.gincaneiro.DatabaseTest;
import com.gincaneiro.entities.Autor;
import com.gincaneiro.entities.Categoria;
import com.gincaneiro.entities.Gincana;
import com.gincaneiro.entities.Tarefa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.gincaneiro.repositories.AutorRepository;
import com.gincaneiro.repositories.CategoriaRepository;
import com.gincaneiro.repositories.GincanaRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author igor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseTest
public class TarefaServiceIT {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private GincanaRepository gincanaRepository;

    @Autowired
    private TarefaService tarefaService;

    private Autor a;

    private Categoria c;

    private Gincana g;

    private Tarefa t1, t2, t3;

    @Before
    public void setUp() throws Exception {
        a = autorRepository.save(new Autor("Einstênio"));
        c = categoriaRepository.save(new Categoria("Charadas"));
        g = gincanaRepository.save(new Gincana("Gincana Municipal de Arroio dos Ratos"));
        t1 = new Tarefa(1L,"Tarefa número 1", "blabla","200 pontos", g, a, c,
                true, LocalDateTime.now());
        t2 = new Tarefa(2L,"Tarefa número 2", "bla","400 pontos", g, a, c,
                true, LocalDateTime.now().minusHours(1));
        t3 = new Tarefa(50L, "Charadazz 2", "blab","300 pontos", g, a, c,
                true, LocalDateTime.now());
        tarefaService.saveTarefa(t1);
        tarefaService.saveTarefa(t2);
        tarefaService.saveTarefa(t3);
    }

    @Test
    public void testEdit() {
        Tarefa t = tarefaService.get(g.getId(), 1L);
        t.setNome("modificada");
        tarefaService.edit(t);
        assertThat(tarefaService.get(g.getId(), 1L).getNome(),equalTo("modificada"));
    }
    @Test
    public void testSave() throws Exception {
        Tarefa t = new Tarefa(99L, "teste", "teste", "teste", g, a, c, true,
                LocalDateTime.now());
        tarefaService.saveTarefa(t);
        assertThat(tarefaService.get(g.getId(),99L).getNome(),equalTo("teste"));
    }
    @Test(expected=Exception.class)
    public void testSaveFail() throws Exception {
        Tarefa t = tarefaService.get(g.getId(),1L);
        tarefaService.saveTarefa(t);
    }

    @Test
    public void testFindAll() {
        assertThat(tarefaService.findAll(g.getId()), hasSize(3));
    }
    @Test
    public void testFindAllVisible_Long() {
        assertThat(tarefaService.findAllVisible(g.getId()), hasSize(3));
    }

    @Test
    public void testFindAllVisible_Long_Pageable() {
        Sort s = new Sort(Sort.Direction.ASC, "nome");
        Pageable page = new PageRequest(0,10, s);
        List<Tarefa> l = tarefaService.findAllVisible(g.getId(),page);
        assertThat(l, hasSize(3));
        assertThat("é do mesmo horário que a t1, mas vem depois por causa do nome",
                   l.get(0), equalTo(t3));
        assertThat(l.get(1), equalTo(t1));
        assertThat(l.get(2), equalTo(t2));

    }

    
}
