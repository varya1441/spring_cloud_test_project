package com.example.authserver.dto;

public class LoginRequest {
    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}