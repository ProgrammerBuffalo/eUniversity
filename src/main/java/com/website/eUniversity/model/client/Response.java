package com.website.eUniversity.model.client;

public class Response<T> {
    private int code;

    private String message;

    private T data;

    public Response<T> success(T data, String message) {
        this.data = data;
        this.message = message;
        this.code = 200;
        return this;
    }

    public Response<T> error(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
