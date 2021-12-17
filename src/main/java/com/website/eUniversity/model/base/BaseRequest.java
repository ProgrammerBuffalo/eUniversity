package com.website.eUniversity.model.base;

import java.util.Date;

public class BaseRequest<T> {
    private T data;

    private final Date date = new Date();

    public BaseRequest(T data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
