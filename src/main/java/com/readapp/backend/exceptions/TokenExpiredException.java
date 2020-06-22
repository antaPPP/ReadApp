package com.readapp.backend.exceptions;


public class TokenExpiredException extends RuntimeException{
    private Long uid;
    private String secret;

    public TokenExpiredException(Long uid, String secret) {
        super();
        this.secret = secret;
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public TokenExpiredException setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public TokenExpiredException setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
