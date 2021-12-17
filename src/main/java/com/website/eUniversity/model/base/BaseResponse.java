package com.website.eUniversity.model.base;

import java.util.Date;

public class BaseResponse<T> {
    private int statusCode;

    private String message;

    private T data;

    private final Date date = new Date();

    public BaseResponse<T> success(T data, String message) {
        this.data = data;
        this.message = message;
        this.statusCode = 200;
        return this;
    }

    public BaseResponse error(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
}
