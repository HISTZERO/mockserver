package com.example.mockserver.service;

import com.example.mockserver.*;
import com.example.mockserver.entity.Project;
import com.example.mockserver.entity.Token;
import com.example.mockserver.repository.ProjectRepository;
import com.example.mockserver.repository.TokenRepository;
import com.example.mockserver.utils.TimeUtil;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.mockserver.Constants.*;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    ProjectService projectService;

    public boolean checkDuplicateCreateProject(String projectName, String apiPrefix){
        return  projectRepository.existsByProjectNameAndApiPrefixAndDeleteFlag(projectName, apiPrefix, NOT_DELETED);
    }

    public boolean checkDuplicateEditProject(Long id, String projectName, String apiPrefix){
        return projectRepository.existsByProjectNameAndApiPrefixAndDeleteFlag(projectName, apiPrefix, NOT_DELETED) && !id.equals(projectRepository.findByProjectNameAndApiPrefixAndDeleteFlag(projectName,apiPrefix, NOT_DELETED).getId()) ;
    }


    public boolean checkProjectExist(Long id){
        return  !projectRepository.existsByIdAndDeleteFlag(id, NOT_DELETED);
    }

    public String listProject(String token, int limit, int offset){
        SuccessProjectContainer successProjectContainer = new SuccessProjectContainer();
        Response response = new Response();
        Token tokenInput = tokenService.findByToken(token).get();
        Long userId = tokenInput.getUserId();
        List<Project> projects = projectRepository.findByUserIdAndDeleteFlag(userId, NOT_DELETED, limit, offset);
        successProjectContainer.setProjectContainer(projects);
        successProjectContainer.setNumberProjects(projectRepository.countProjectsByUserIdAndAndDeleteFlag(userId, NOT_DELETED));
        response.setSuccessContainer(successProjectContainer);
        response.setErrorContainer(null);
        response.setApiStatus(OK);
        return JSON.encode(response);
    }

    public String addProject (String projectName, String apiPrefix , String token){
        Project project = new Project();
        SuccessProjectContainer successProjectContainer = new SuccessProjectContainer();
        Response response = new Response();
        Optional<Token> tokenInput = tokenRepository.findByToken(token);

        Long userId = tokenInput.get().getUserId();
        project.setProjectName(projectName);
        project.setApiPrefix(apiPrefix);
        project.setUserId(userId);
        project.setCreatedDatetime(TimeUtil.getTime());
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projectRepository.save(project);
        response.setApiStatus(OK);
        response.setErrorContainer(null);
        successProjectContainer.setMessage(SUCCESSFULLY_MESSAGE);
        successProjectContainer.setProjectContainer(projects);
        response.setSuccessContainer(successProjectContainer);
        response.setApiStatus(OK);
        return JSON.encode(response);
    }


    public String editProject(String projectName, String apiPrefix, Long id){
            Response response = new Response();
            SuccessProjectContainer successProjectContainer = new SuccessProjectContainer();
            Optional<Project> projectNow = projectRepository.findById(id);
            Timestamp createdDatetime =   projectNow.get().getCreatedDatetime();
            projectNow.get().setProjectName(projectName);
            projectNow.get().setApiPrefix(apiPrefix);
            projectNow.get().setUpdatedDatetime(TimeUtil.getTime());
            projectNow.get().setCreatedDatetime(createdDatetime);
            projectRepository.save(projectNow.get());
            response.setErrorContainer(null);
            List<Project> projects = new ArrayList<>();
            projects.add(projectNow.get());
            successProjectContainer.setProjectContainer(projects);
            successProjectContainer.setMessage(SUCCESSFULLY_MESSAGE);
            response.setSuccessContainer(successProjectContainer);
            response.setApiStatus(OK);
            return JSON.encode(response);
    }

    public String deleteProject(Long id){
        Response response = new Response();
        SuccessContainer successContainer = new SuccessContainer();
        Optional<Project> projectNow = projectRepository.findById(id);
        projectNow.get().setDeleteFlag(DELETED);
        projectRepository.save(projectNow.get());
        successContainer.setMessage(SUCCESSFULLY_MESSAGE);
        response.setApiStatus(OK);
        response.setSuccessContainer(successContainer);
        response.setErrorContainer(null);
        return JSON.encode(response);
    }

}
