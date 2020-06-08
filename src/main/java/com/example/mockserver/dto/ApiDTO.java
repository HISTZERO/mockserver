package com.example.mockserver.dto;

public class ApiDTO {
    private Long id;
    private int codeValue;
    private String response;

    private String path;

    private String name;

    private String content;

    private int deleteFlag;

    public ApiDTO(Long id, int codeValue, String response, String path, String name, String content, int deleteFlag) {
        this.id = id;
        this.codeValue = codeValue;
        this.response = response;
        this.path = path;
        this.name = name;
        this.content = content;
        this.deleteFlag = deleteFlag;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
