/*
 * Copyright (c) 2016 Igor Pires Ferreira
 * ipferreira@inf.ufrgs.br
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 * In no event shall the author be liable for any claim or damages.
 *
 * Todos os direitos reservados.
 */
package com.gincaneiro.repositories;

import com.gincaneiro.DatabaseTest;
import com.gincaneiro.entities.Autor;
import com.gincaneiro.entities.Categoria;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.gincaneiro.entities.Gincana;
import com.gincaneiro.entities.Tarefa;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author igor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseTest
public class TarefaRepositoryIT {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private GincanaRepository gincanaRepository;

    private Autor a1, a2;

    private Categoria c1, c2;

    private Gincana g1, g2;

    @Before
    public void setUp() {
        a1 = autorRepository.save(new Autor("Einstênio"));
        a2 = autorRepository.save(new Autor("Titânio"));
        c1 = categoriaRepository.save(new Categoria("Charadas"));
        c2 = categoriaRepository.save(new Categoria("Esportivas"));
        g1 = gincanaRepository.save(new Gincana("Gincana Municipal de Arroio dos Ratos"));
        g2 = gincanaRepository.save(new Gincana("Gincana Municipal de Vacaria"));
        List<Tarefa> l = asList(
                new Tarefa(1L, "Tarefa1", "a", "a", g1, a1, c1, true,
                        LocalDateTime.now()),
                new Tarefa(2L, "Tarefa2", "a", "a", g1, a1, c2, true,
                        LocalDateTime.now().plusHours(1)),
                new Tarefa(4L, "Tarefa4", "a", "a", g1, a1, c1, false,
                        LocalDateTime.now().minusMinutes(5)),
                new Tarefa(1L, "Tarefa", "a", "a", g2, a1, c1, true,
                        LocalDateTime.now())
        );

        tarefaRepository.save(l);
    }

    @Test
    public void testCountByCategoria() {
        assertThat(tarefaRepository.count(), equalTo(4L));
    }



}
