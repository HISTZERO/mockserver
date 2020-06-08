package com.example.mockserver;

public class UserContainer {
    private long id;
    private String token;

    public  UserContainer(){}
    public UserContainer(long id, String token) {
        this.id = id;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
