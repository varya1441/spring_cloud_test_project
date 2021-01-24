package com.example.userservice.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class AuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
               http.authorizeRequests()
                    .antMatchers("/**", "/login**")
                    .permitAll()//  that url allowed by anyone
                    .anyRequest()//
                    .authenticated();//bu login user
        }
    }

