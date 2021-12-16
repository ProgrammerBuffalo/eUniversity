package com.website.eUniversity.service;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IFilterExceptionHandler {

    void handleException(HttpServletResponse response, HttpStatus status, Exception ex) throws IOException;
}
