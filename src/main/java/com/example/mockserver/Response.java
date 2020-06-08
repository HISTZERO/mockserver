package com.example.mockserver;

public class Response {
    public String apiStatus;
    public ErrorContainer errorContainer;
    public SuccessContainer successContainer;


    public Response() {}

    public Response(String apiStatus, ErrorContainer errorContainer, SuccessContainer successContainer) {
        this.apiStatus = apiStatus;
        this.errorContainer = errorContainer;
        this.successContainer = successContainer;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public ErrorContainer getErrorContainer() {
        return errorContainer;
    }

    public void setErrorContainer(ErrorContainer errorContainer) {
        this.errorContainer = errorContainer;
    }

    public void setSuccessContainer(SuccessContainer successContainer) {
        this.successContainer = successContainer;
    }

    public SuccessContainer getSuccessContainer() {
        return successContainer;
    }
}


