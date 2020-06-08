package com.example.mockserver.controller;

import com.example.mockserver.ErrorContainer;
import com.example.mockserver.Response;
import static com.example.mockserver.Constants.*;
import com.example.mockserver.SuccessContainer;
import com.example.mockserver.request.LoginRequest;
import com.example.mockserver.request.SignupRequest;
import com.example.mockserver.request.TokenRequest;
import com.example.mockserver.service.TokenService;
import com.example.mockserver.service.UserService;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/mock-server")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/signup")
    public @ResponseBody
    String signup(@Valid @RequestBody SignupRequest signUpRequest, BindingResult bindingResult) {
        SuccessContainer successContainer = new SuccessContainer();
        ErrorContainer errorContainer = new ErrorContainer();
        Response response = new Response();

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldError = bindingResult.getFieldErrors();
            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                errorContainer.createMessage(fieldError.get(i).getField(), fieldError.get(i).getDefaultMessage());
            }
            errorContainer.setErrorCode(INVALID_PARAMETER);
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        boolean isUserNameExist = userService.checkUserNameExist(signUpRequest.getUsername());
        if (isUserNameExist) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(USER_NAME_FIELD, "Username " + FIELD_ALREADY_EXISTS_MESSAGE);
        }
        boolean isEmailExist = userService.checkEmailExist(signUpRequest.getEmail());
        if (isEmailExist) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(EMAIL_FIELD, "Email " + FIELD_ALREADY_EXISTS_MESSAGE);
        }

        boolean isConfirmPasswordMatch = userService.checkConfirmPasswordMatch(signUpRequest.getPassword(), signUpRequest.getConfirmPassword());
        if (isConfirmPasswordMatch) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(CONFIRM_PASSWORD, CONFIRM_PASSWORD_MESSAGE);
        }

        if (isUserNameExist || isEmailExist || isConfirmPasswordMatch) {
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }

        userService.registerUser(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
        response.setApiStatus(OK);
        response.setErrorContainer(null);
        successContainer.setMessage(SUCCESSFULLY_MESSAGE);
        response.setSuccessContainer(successContainer);
        return JSON.encode(response);

    }

    @PostMapping(path = "/login")
    public @ResponseBody
    String login(@RequestBody(required = false) @Valid LoginRequest loginRequest, BindingResult bindingResult) {

        Response response = new Response();
        ErrorContainer errorContainer = new ErrorContainer();

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldError = bindingResult.getFieldErrors();

            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                errorContainer.createMessage(fieldError.get(i).getField(), fieldError.get(i).getDefaultMessage());
            }
            errorContainer.setErrorCode(INVALID_PARAMETER);

            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }

        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping(path = "/logout")
    public @ResponseBody
    String logOut(@RequestBody TokenRequest tokenRequest) {
        String checkTokenResponse = tokenService.checkValidToken(tokenRequest.getToken());
        if (!checkTokenResponse.equals("")) {
            return checkTokenResponse;
        }
        return userService.logout(tokenRequest.getToken());
    }
}

