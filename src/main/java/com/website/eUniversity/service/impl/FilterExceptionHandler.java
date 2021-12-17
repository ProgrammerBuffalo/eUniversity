package com.website.eUniversity.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.service.IFilterExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class FilterExceptionHandler implements IFilterExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleException(HttpServletResponse response, HttpStatus status, Exception ex) throws IOException {

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write(objectMapper.writeValueAsString
                (new BaseResponse<String>().error(ex.getMessage(), status.value())));
    }
}
