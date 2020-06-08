package com.example.mockserver.request;

import javax.validation.constraints.NotNull;

public class ApiRequest extends ListProjectRequest{

    public ApiRequest(@NotNull(message = "Token cannot be null") String token, int limit, int offset) {
        super(token, limit, offset);
    }
}
