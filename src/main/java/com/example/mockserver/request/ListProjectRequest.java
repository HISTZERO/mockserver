package com.example.mockserver.request;

import javax.validation.constraints.NotNull;

public class ListProjectRequest extends TokenRequest {


    private int limit;
    private int offset;

    public ListProjectRequest(@NotNull(message = "Token cannot be null") String token, int limit, int offset) {
        super(token);
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


}
