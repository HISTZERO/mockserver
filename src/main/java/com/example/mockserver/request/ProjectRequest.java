package com.example.mockserver.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectRequest {

    @NotNull(message = "Token is required")
    @Column(length = 32)
    private String token;

    @NotEmpty(message = "Project name is required")
    @NotNull(message = "Project name is required")
    @NotBlank(message = "Project name may not be blank")
    @Size(min = 6, max = 255, message = "Project name is from 6 to 255 characters ")
    private String projectName;

    @Size( max = 255, message = "Api Prefix is less than 255 characters ")
    private String apiPrefix;

    public ProjectRequest(){}

    public ProjectRequest(String token, String projectName) {
        this.token = token;
        this.projectName = projectName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiPrefix) {
        this.apiPrefix = apiPrefix;
    }
}
