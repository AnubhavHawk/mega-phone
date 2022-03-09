package com.bbps.token.rest.model;

import java.util.Date;

public class JwtResponse {
    String token;
    Date expiry;

    public JwtResponse(String token, Date expiry) {
        this.token = token;
        this.expiry = expiry;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
