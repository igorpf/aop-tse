package com.gincaneiro.controllers;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

public class AuthenticationMocks {

    private AuthenticationMocks() {
    }

    public static Authentication regularAuthentication() {
        GrantedAuthority simpleAuthority = new SimpleGrantedAuthority("USER");
        User regularUser = new User("user", "password", Arrays.asList(simpleAuthority));
        return new TestingAuthenticationToken(regularUser, null, "ROLE_USER");
    }

    public static Authentication payingAuthentication() {
        GrantedAuthority payingAuthority = new SimpleGrantedAuthority("PAYING");
        User payingUser = new User("user", "password", Arrays.asList(payingAuthority));
        return new TestingAuthenticationToken(payingUser, null, "ROLE_ADMIN");
    }
}