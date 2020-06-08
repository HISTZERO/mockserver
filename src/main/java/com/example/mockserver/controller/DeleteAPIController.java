package com.example.mockserver.controller;

import com.example.mockserver.service.ApiService;
import com.example.mockserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mock-server/project/api")
public class DeleteAPIController {

    @Autowired
    TokenService tokenService;

    @Autowired
    ApiService apiService;

    @PostMapping(path = "/delete")
    public @ResponseBody
    String deleteApi(@RequestParam String token, @RequestParam Long id){
        if(!tokenService.checkValidToken(token).equals("")){
            return tokenService.checkValidToken(token);
        }

        if(!apiService.checkApiId(id).equals("")){
            return apiService.checkApiId(id);
        }

        return apiService.deleteApi(id);
    }

}
