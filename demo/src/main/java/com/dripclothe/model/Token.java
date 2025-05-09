package com.dripclothe.model;

//Instead sending all the payment intent object we send the clientId secret in this token
public class Token {
    String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
