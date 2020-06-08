package com.example.mockserver;

import java.lang.reflect.Array;
import java.util.*;

public class ErrorContainer {
    private String errorCode ;
    private Map<String, String> message = new HashMap<>();


    public ErrorContainer() {
    }


    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    //TODO doi lai ten phuong thuc
    public void createMessage(String filed, String message) {
        this.message.put(filed, message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
