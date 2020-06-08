package com.example.mockserver.controller;


import com.example.mockserver.ErrorContainer;
import com.example.mockserver.Response;
import com.example.mockserver.SuccessContainer;
import com.example.mockserver.service.ApiService;
import com.example.mockserver.service.ProjectService;
import com.example.mockserver.service.TokenService;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.mockserver.Constants.*;
import static com.example.mockserver.Constants.BAD;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mock-server/project/api")
public class APIController {
    @Autowired
    TokenService tokenService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ApiService apiService;


    @PostMapping(path = "/list")
    @ResponseBody
    public String listApi(@RequestParam String token, @RequestParam int limit,
                          @RequestParam int offset , @RequestParam Long projectId) {

        ErrorContainer errorContainer = new ErrorContainer();
        Response response = new Response();

        String checkValidTokenResponse = tokenService.checkValidToken(token);
        if(!checkValidTokenResponse.equals("")){
            return tokenService.checkValidToken(token);
        }

        if(projectService.checkProjectExist(projectId)){
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PROJECT, "Project" + NOT_EXISTED_MESSAGE);
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }

        return apiService.listApi(limit, offset,projectId);
    }
}
