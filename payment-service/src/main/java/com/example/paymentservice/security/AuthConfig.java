package com.example.paymentservice.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class AuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
               http.authorizeRequests()
                    .antMatchers("/**")
                    .permitAll();//  that url allowed by anyone

        }
    }

