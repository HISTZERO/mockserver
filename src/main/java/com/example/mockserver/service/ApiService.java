package com.example.mockserver.service;

import com.example.mockserver.ErrorContainer;
import com.example.mockserver.Response;
import com.example.mockserver.SuccessApiContainer;
import com.example.mockserver.SuccessContainer;
import com.example.mockserver.dto.ApiDTO;
import com.example.mockserver.entity.Api;
import com.example.mockserver.entity.Status;
import com.example.mockserver.repository.ApiRepository;
import com.example.mockserver.repository.StatusRepository;
import com.example.mockserver.utils.TimeUtil;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.mockserver.Constants.*;

@Service
public class ApiService {

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    StatusRepository statusRepository;


    Response response = new Response();
    SuccessApiContainer successApiContainer = new SuccessApiContainer();
    ErrorContainer errorContainer = new ErrorContainer();
    SuccessContainer successContainer = new SuccessContainer();

    public String checkStatusCodeId(Long statusCodeId) {
        if (!statusRepository.existsById(statusCodeId)) {
            errorContainer.setErrorCode(STATUS_NOT_FOUND);
            errorContainer.createMessage(STATUS, "Status"+ NOT_EXISTED_MESSAGE);
            response.setSuccessContainer(null);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            return JSON.encode(response);
        }
        return "";
    }

    public String validateInput(String name, String content, String path) {
        if (name.length() > MAX_SIZE) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(API_NAME, "Api name" + FIELD_LONG_MESSAGE);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }

        if (content.length() > CONTENT_MAX_SIZE) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(CONTENT, "Content" + FIELD_LONG_MESSAGE);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        if (path.length() > MAX_SIZE) {
            errorContainer.setErrorCode(INVALID_PARAMETER);
            errorContainer.createMessage(PATH, "Path" + FIELD_LONG_MESSAGE);
            response.setApiStatus(BAD);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        return "";
    }

    public String checkDuplicateApi(String name, String path){
        if(apiRepository.existsByNameAndPath(name, path)){
            errorContainer.setErrorCode(API_EXISTED);
            errorContainer.createMessage(API, "API" + FIELD_ALREADY_EXISTS_MESSAGE);
            response.setErrorContainer(errorContainer);
            response.setApiStatus(BAD);
            response.setSuccessContainer(null);
            return JSON.encode(response);
        }
        return "";
    }

    public String checkApiId(Long id) {
        if (!apiRepository.existsByIdAndDeleteFlag(id, NOT_DELETED)) {
            errorContainer.setErrorCode(API_NOT_FOUND);
            errorContainer.createMessage(API, "API" + NOT_EXISTED_MESSAGE);
            response.setErrorContainer(errorContainer);
            response.setSuccessContainer(null);
            response.setApiStatus(BAD);
            return JSON.encode(response);
        }
        return "";
    }

    public String listApi(int limit, int offset, long projectId) {
        List<ApiDTO> apis = apiRepository.fetchApiDTO(projectId);
        successApiContainer.setApiContainer(apis);
        response.setSuccessContainer(successApiContainer);
        response.setApiStatus(OK);
        response.setErrorContainer(null);
        return JSON.encode(response);
    }

    public String createApi(String name, String path, Long projectId,
                            Long statusCodeId, String content) {
        Api apiInput = new Api();
        Optional<Status> status = statusRepository.findById(statusCodeId);
        apiInput.setContent(content);
        apiInput.setName(name);
        apiInput.setPath(path);
        apiInput.setProjectId(projectId);
        apiInput.setStatus(status.get());

        apiInput.setDeleteFlag(NOT_DELETED);
        apiInput.setCreatedDatetime(TimeUtil.getTime());
        apiRepository.save(apiInput);

        Optional<Api> apiCreated = apiRepository.findByNameAndPath(name, path);
        Long idNow = apiCreated.get().getId();
        List<ApiDTO> apiDto = apiRepository.getApiDTO(idNow);
        successApiContainer.setApiContainer(apiDto);
        response.setErrorContainer(null);
        response.setApiStatus(OK);
        response.setSuccessContainer(successApiContainer);
        return JSON.encode(response);

    }

    public String editApi(String name, String path,
                          Long statusCodeId, String content, Long id) {
        Optional<Api> apiNow = apiRepository.findById(id);
        Optional<Status> status = statusRepository.findById(statusCodeId);
        apiNow.get().setName(name);
        apiNow.get().setPath(path);
        apiNow.get().setStatus(status.get());

        apiNow.get().setContent(content);
        apiNow.get().setUpdatedDatetime(TimeUtil.getTime());
        apiRepository.save(apiNow.get());
        List<ApiDTO> apiDto = apiRepository.getApiDTO(id);
        successApiContainer.setApiContainer(apiDto);
        response.setErrorContainer(null);
        response.setApiStatus(OK);
        response.setSuccessContainer(successApiContainer);
        return JSON.encode(response);

    }

    public String deleteApi(Long id){
        Optional<Api> apiNow = apiRepository.findById(id);
        apiNow.get().setDeleteFlag(DELETED);
        apiRepository.save(apiNow.get());
        response.setErrorContainer(null);
        successContainer.setMessage(SUCCESSFULLY_MESSAGE);
        response.setSuccessContainer(successContainer);
        response.setApiStatus(OK);
        return JSON.encode(response);
    }

}
