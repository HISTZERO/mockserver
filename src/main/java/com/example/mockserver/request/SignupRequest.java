package com.example.mockserver.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SignupRequest extends LoginRequest {

    @NotNull(message = "Email is required!")
    @NotEmpty(message = "Email is required!")
    @Email(message = "Wrong email format!")
    private String email;

    @NotNull(message = "Confirm password is required!")
    @NotEmpty(message = "Confirm password is required!")
    private String confirmPassword;

    public SignupRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

