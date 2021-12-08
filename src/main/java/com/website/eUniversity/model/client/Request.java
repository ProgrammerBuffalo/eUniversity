package com.website.eUniversity.model.client;

import java.util.Date;

public class Request<T> {
    private T data;

    private Date date;

    public Request(T data) {
        this.date = new Date();
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
