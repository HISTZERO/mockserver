package com.example.mockserver.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotEmpty(message = "Username is required")
    @NotNull(message = "Username is required")
    @NotBlank(message = "User name may not be blank")
    @Size(min = 6, max = 255, message = "User name is from 6 to 255 characters ")
    private String username;

    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password may not be blank")
    @Size(min = 6 , message = "Password is at least 6 characters ")
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



