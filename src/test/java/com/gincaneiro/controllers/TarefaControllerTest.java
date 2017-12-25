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

import com.gincaneiro.WebTest;
import com.gincaneiro.entities.Tarefa;
import com.gincaneiro.services.TarefaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author igor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebTest
public class TarefaControllerTest {
    
    @Mock
    private TarefaService service;
    
    @Autowired
    @InjectMocks
    private TarefaController controller;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;
    
    private Tarefa t;

    protected void setRegularUserAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(AuthenticationMocks.regularAuthentication());
    }

    protected void setPayingUserAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(AuthenticationMocks.payingAuthentication());
    }
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity(springSecurityFilterChain))
                .build();
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(controller, "service", service);
        t = new Tarefa();
        t.setNome("Charada de teste");
        t.setCorpo("Entreguem o que estamos pedindo");
        t.setId(1L);
        when(service.get(1L,t.getId())).thenReturn(t);
        when(service.exists(1L,t.getId())).thenReturn(false);
        setRegularUserAuthentication();
    }

    @Test
    public void testGet() throws Exception {
        setPayingUserAuthentication();
        mockMvc.perform(get("/tarefa/1/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(t.getId().intValue())))
                .andExpect(jsonPath("$.nome", equalTo(t.getNome())))
                .andExpect(jsonPath("$.corpo", equalTo(t.getCorpo())));
    }
    @Test
    public void testGetNoAuthority() throws Exception {
        mockMvc.perform(get("/tarefa/1/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    @Test
    public void testSave() throws Exception {

        String json = "{\"nome\":\"Tarefa\", \"id\":\"2\", \"corpo\":\"2\", "
                + "\"pontuacao\":\"200 pontos\", \"dataPostagem\":\"06/07/2016 17:05\","
                + "\"autor\":{\"id\":\"1\",\"nome\":\"einstenio\"},"
                + "\"categoria\":{\"id\":\"1\",\"nome\":\"charadas\"}, "
                + "\"gincana\":{\"id\":\"1\",\"nome\":\"Gincana de ratos\"}}";
        mockMvc.perform(
                post("/tarefa/save")
                        .content(json).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
        verify(service).saveTarefa(any());
    }
    /**
     * Test if the @Valid annotation works
     * @throws Exception 
     */
    @Test
    public void testSaveError() throws Exception {
        String json = "{\"nome\":\"abcdefghijklmnopqrstu\"}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        json = "{}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    
}
