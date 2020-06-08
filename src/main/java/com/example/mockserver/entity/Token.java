package com.example.mockserver.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String token;

    private Timestamp availableTime;


    public Token(Long userId, String token, Timestamp availableTime) {
        this.userId = userId;
        this.token = token;
        this.availableTime = availableTime;
    }

    public Token() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(Timestamp availableTime) {
        this.availableTime = availableTime;
    }
}
