package com.cyq7on.crud.common.security;


import org.apache.shiro.authc.AuthenticationToken;


public class UserToken implements AuthenticationToken {

    // 密钥
    private String token;

    public UserToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
