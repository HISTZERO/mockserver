package com.example.mockserver.request;

import javax.validation.constraints.NotNull;

public class TokenRequest {

    @NotNull(message = "Token cannot be null")
    private String token;

    public TokenRequest(@NotNull(message = "Token cannot be null") String token) {
        this.token = token;
    }
    public TokenRequest(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
