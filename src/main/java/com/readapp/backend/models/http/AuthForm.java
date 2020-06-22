package com.readapp.backend.models.http;

public class AuthForm {
    String token;
    Long id;

    public String getToken() {
        return token;
    }

    public AuthForm setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AuthForm setId(Long id) {
        this.id = id;
        return this;
    }
}
