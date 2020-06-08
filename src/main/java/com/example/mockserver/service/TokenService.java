package com.example.mockserver.service;

import com.example.mockserver.ErrorContainer;
import com.example.mockserver.Response;
import com.example.mockserver.entity.Token;
import com.example.mockserver.repository.TokenRepository;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.mockserver.Constants.*;
import java.util.Optional;
import static com.example.mockserver.Constants.BAD;


@Service
public class TokenService {

    @Autowired
    public TokenRepository tokenRepository;

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteById(Long id) {
        tokenRepository.deleteById(id);
    }

    public String checkValidToken(String token) {
        Response response = new Response();
        ErrorContainer errorContainer = new ErrorContainer();
        response.setApiStatus(BAD);
        errorContainer.setErrorCode(INVALID_PARAMETER);
        response.setSuccessContainer(null);
        if (checkLength(token, TOKEN_SIZE)) {
            errorContainer.createMessage(TOKEN, TOKEN_SIZE_MESSAGE);
            response.setErrorContainer(errorContainer);
            return JSON.encode(response);
        }

        if(!checkTokenExistAndNotExpired(token)) {
            errorContainer.createMessage(TOKEN, TOKEN_INVALID_MESSAGE);
            response.setErrorContainer(errorContainer);
            return JSON.encode(response);
        }
        return "";
    }

    private boolean checkLength(String input, int validSize) {
        return input.length() != validSize;
    }

    private boolean checkTokenExistAndNotExpired(String token) {
        return tokenRepository.checkValidToken(token).isPresent();
    }
}
