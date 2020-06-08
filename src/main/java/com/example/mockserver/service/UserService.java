package com.example.mockserver.service;

import com.example.mockserver.*;
import net.arnx.jsonic.JSON;
import com.example.mockserver.entity.Token;
import com.example.mockserver.entity.User;

import static com.example.mockserver.Constants.*;

import com.example.mockserver.repository.TokenRepository;
import com.example.mockserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;

@Service
public class UserService {
    final String secretKey = "SecretKey";
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenService tokenService;

    boolean isErrorRegister = false;


    public boolean isErrorRegister() {
        return isErrorRegister;
    }

    public void setErrorRegister(boolean errorRegister) {
        isErrorRegister = errorRegister;
    }


    public boolean checkUserNameExist(String username) {
        return userRepository.existsByUsername(username);

    }

    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkConfirmPasswordMatch(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    public void registerUser(String username, String email, String password) {
        Response response = new Response();
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        String encryptedPassword = AES.encrypt(password, secretKey);
        newUser.setPassword(encryptedPassword);
        userRepository.save(newUser);
    }

    public String login(String username, String password) {
        Response response = new Response();
        String encryptedPassword = AES.encrypt(password, secretKey);
        SuccessUserContainer successUserContainer = new SuccessUserContainer();
        ErrorContainer errorContainer = new ErrorContainer();

        if (userRepository.existsByUsernameAndPassword(username, encryptedPassword)) {
            User user = userRepository.findByUsername(username);
            long userId = user.getId();
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            String token = base64Encoder.encodeToString(randomBytes);
            Token tokenCreated = new Token();
            tokenCreated.setToken(token);
            tokenCreated.setUserId(userId);
            Timestamp availableTime = new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60 * 3));
            tokenCreated.setAvailableTime(availableTime);
            tokenRepository.save(tokenCreated);
            response.setApiStatus(OK);
            response.setErrorContainer(null);
            successUserContainer.setId(userId);
            successUserContainer.setToken(token);
            successUserContainer.setMessage(SUCCESSFULLY_MESSAGE);
            response.setSuccessContainer(successUserContainer);
            response.setErrorContainer(null);
            return JSON.encode(response);
        } else {
            errorContainer.setErrorCode(WRONG_INFORMATION);
            errorContainer.createMessage(USER_NAME_FIELD + " or " + PASSWORD_FIELD, " is not true");
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
    }

    public String logout(String token) {
        Response response = new Response();
        SuccessContainer successContainer = new SuccessContainer();
        Token tokenInput = tokenService.findByToken(token).get();
        Long id = tokenInput.getId();
        tokenService.deleteById(id);
        response.setErrorContainer(null);
        successContainer.setMessage(SUCCESSFULLY_MESSAGE);
        response.setSuccessContainer(successContainer);
        response.setApiStatus(OK);
        return JSON.encode(response);
    }
}

