package com.example.mockserver.controller;

import com.example.mockserver.service.ApiService;
import com.example.mockserver.service.ProjectService;
import com.example.mockserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mock-server/project/api")
public class UpdateAPIController {

    @Autowired
    TokenService tokenService;

    @Autowired
    ApiService apiService;

    @Autowired
    ProjectService projectService;


    @PostMapping(path = "/edit" )
    public @ResponseBody
    String editApi(@RequestParam String token, @RequestParam Long statusCodeId
            , @RequestParam Long id, @RequestParam String path, @RequestParam String name,
                     @RequestParam String content){
        if(!tokenService.checkValidToken(token).equals(""))
        {
            return tokenService.checkValidToken(token);
        }

        if (!apiService.checkStatusCodeId(statusCodeId).equals("")){
            return apiService.checkStatusCodeId(statusCodeId);
        }

        if(!apiService.checkApiId(id).equals("")){
            return apiService.checkApiId(id);
        }

        if(!apiService.validateInput(name, content, path).equals("")){
            return apiService.validateInput(name, content, path);
        }

        if (!apiService.checkDuplicateApi(name, path).equals("")){
            return apiService.checkDuplicateApi(name, path);
        }

        return apiService.editApi(name, path, statusCodeId, content, id);
    }
}
