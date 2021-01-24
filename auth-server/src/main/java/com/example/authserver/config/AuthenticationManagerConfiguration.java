package com.example.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("varya")
                .password(passwordEncoder.encode("pass1"))
                .roles("EMPLOYEE").and()
                .withUser("nastya").password(passwordEncoder.encode("pass2")).roles("ADMIN");
    }

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


}
