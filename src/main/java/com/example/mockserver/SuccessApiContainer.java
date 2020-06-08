package com.example.mockserver;

import com.example.mockserver.dto.ApiDTO;

import java.util.List;

public class SuccessApiContainer extends SuccessContainer {
    private List<ApiDTO> apiContainer;



    public List<ApiDTO> getApiContainer() {
        return apiContainer;
    }

    public void setApiContainer(List<ApiDTO> apiContainer) {
        this.apiContainer = apiContainer;
    }
}
