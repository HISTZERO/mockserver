package com.example.mockserver;

import com.example.mockserver.entity.Project;

import java.util.List;

public class SuccessProjectContainer extends SuccessContainer {
    private List<Project> projectContainer;
    private int numberProjects;

    public int getNumberProjects() {
        return numberProjects;
    }

    public void setNumberProjects(int numberProjects) {
        this.numberProjects = numberProjects;
    }

    public List<Project> getProjectContainer() {
        return projectContainer;
    }

    public void setProjectContainer(List<Project> projectContainer) {
        this.projectContainer = projectContainer;
    }
}
