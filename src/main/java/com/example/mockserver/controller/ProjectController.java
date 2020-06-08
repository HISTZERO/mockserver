package com.example.mockserver.controller;

import com.example.mockserver.ErrorContainer;
import com.example.mockserver.Response;
import com.example.mockserver.request.ListProjectRequest;
import com.example.mockserver.request.ProjectRequest;
import com.example.mockserver.service.ProjectService;
import com.example.mockserver.service.TokenService;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static com.example.mockserver.Constants.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mock-server/project")
public class ProjectController {

    @Autowired
    TokenService tokenService;

    @Autowired
    ProjectService projectService;

    Response response = new Response();
    ErrorContainer errorContainer = new ErrorContainer();

    @PostMapping(path = "/list")
    public @ResponseBody
    String listProject(@RequestBody ListProjectRequest listProjectRequest) {

        String isValidTokenResponse = tokenService.checkValidToken(listProjectRequest.getToken());
        if (!isValidTokenResponse.equals("")) {
            return isValidTokenResponse;
        }

        return projectService.listProject(listProjectRequest.getToken(), listProjectRequest.getLimit(), listProjectRequest.getOffset());
    }

    @PostMapping(path = "/create")
    public @ResponseBody
    String addNewProject(@Valid @RequestBody(required = false) ProjectRequest createProjectRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Response response = new Response();
            ErrorContainer errorContainer = new ErrorContainer();
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

        String checkTokenResponse = tokenService.checkValidToken(createProjectRequest.getToken());
        if (!checkTokenResponse.equals("")) {
            return checkTokenResponse;
        }

        if (projectService.checkDuplicateCreateProject(createProjectRequest.getProjectName(), createProjectRequest.getApiPrefix())) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PROJECT, "Project " + FIELD_ALREADY_EXISTS_MESSAGE);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        return projectService.addProject(createProjectRequest.getProjectName(), createProjectRequest.getApiPrefix(), createProjectRequest.getToken());
    }

    @PostMapping(path = "/edit/{id}")
    public @ResponseBody
    String updateProject(@PathVariable Long id, @Valid @RequestBody(required = false) ProjectRequest updateProjectRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = new Response();
            ErrorContainer errorContainer = new ErrorContainer();
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

        String checkTokenResponse = tokenService.checkValidToken(updateProjectRequest.getToken());
        if (!checkTokenResponse.equals("")) {
            return checkTokenResponse;
        }

        boolean checkProjectExistResponse = projectService.checkProjectExist(id);
        if (checkProjectExistResponse) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PROJECT, "Project" + NOT_EXISTED_MESSAGE);
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }

        boolean checkDuplicateProjectResponse = projectService.checkDuplicateEditProject(id,updateProjectRequest.getProjectName(), updateProjectRequest.getApiPrefix());
        if (checkDuplicateProjectResponse) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PROJECT, "Project " + FIELD_ALREADY_EXISTS_MESSAGE);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        return projectService.editProject(updateProjectRequest.getProjectName(), updateProjectRequest.getApiPrefix(), id);
    }

    @PostMapping(path = "/delete/{id}")
    @ResponseBody
    public String deleteProject(@RequestBody(required = false) ProjectRequest projectRequest, @PathVariable("id") long id ){

        String checkValidTokenResponse = tokenService.checkValidToken(projectRequest.getToken());
        if(!checkValidTokenResponse.equals("")){
            return checkValidTokenResponse;
        }

        boolean checkProjectExistResponse = projectService.checkProjectExist(id);
        if(checkProjectExistResponse){
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PROJECT, "Project" + NOT_EXISTED_MESSAGE);
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        return projectService.deleteProject(id);
    }
}


