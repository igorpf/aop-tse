package com.gincaneiro.config.security;

/**
 * Created by igor on 02/03/17.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception { // NOSONAR - spring throws this
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").authenticated();
//        block everything else
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/**")
//                .denyAll().antMatchers( "/**").denyAll();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        GrantedAuthority simpleAuthority = new SimpleGrantedAuthority("USER"),
                payingAuthority = new SimpleGrantedAuthority("PAYING");
        UserDetails regularUser = new User("user", "password", Arrays.asList(simpleAuthority)),
                payingUser = new User("paying", "password", Arrays.asList(simpleAuthority, payingAuthority));
        return new InMemoryUserDetailsManager(Arrays.asList(regularUser, payingUser));
    }
}
