package com.example.mockserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codeValue;

    private String response;

//    @OneToMany(targetEntity = Api.class , mappedBy = "status", orphanRemoval = false, fetch = FetchType.LAZY)
//    private Set<Api> apis;

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(int codeValue) {
        this.codeValue = codeValue;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
