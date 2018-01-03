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
import com.gincaneiro.entities.Autor;
import com.gincaneiro.services.AutorService;

import static com.gincaneiro.TestUtils.setPayingUserAuthentication;
import static com.gincaneiro.TestUtils.setRegularUserAuthentication;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

/**
 *
 * @author igorpiresferreira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebTest
public class AutorControllerTest {

    @Mock
    private AutorService service;

    @InjectMocks
    @Autowired
    private AutorController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    private Autor a = new Autor();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity(springSecurityFilterChain))
                .build();

        ReflectionTestUtils.setField(controller, "service", service);
        a.setId(1L);
        a.setNome("Einstênio");
        when(service.get(a.getId())).thenReturn(a);
        setPayingUserAuthentication();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/autor/{id}", a.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(a.getId().intValue())))
                .andExpect(jsonPath("$.nome", equalTo(a.getNome())));
        verify(service).get(1L);
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get("/autor/{id}", 99L ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(service).get(99L);
    }

    @Test
    public void testGetNoAuth() throws Exception {
        setRegularUserAuthentication();
        mockMvc.perform(get("/autor/{id}", a.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        verifyZeroInteractions(service);
    }

    @Test
    public void testSave() throws Exception {
        String json = "{\"nome\":\"Nióbio\"}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).save(any());
    }

    /**
     * Test if the @Valid annotation works
     * @throws Exception 
     */
    @Test
    public void testSaveError() throws Exception {
        String json = "{\"nome\":\"\"}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        json = "{\"nome\":\"abcdefghijklmnopqrstu\"}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        json = "{}";
        mockMvc.perform(post("/autor/save").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
