package com.example.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@RestController
public class UserResource implements Serializable {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;

    }

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @RequestMapping(value = "/getSomething")
    public String getSection(OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = tokenServices.getAccessToken(authentication).getAdditionalInformation();

        String customInfo = (String) additionalInfo.get("customInfo");
        Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) additionalInfo.get("authorities");

        return customInfo;
    }
}
