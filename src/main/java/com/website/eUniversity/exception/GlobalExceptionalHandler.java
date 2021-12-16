package com.website.eUniversity.exception;

import com.website.eUniversity.model.client.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(Exception.class)
    public Response<?> exceptionCatch(Exception ex) {
        return new Response<>().error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

}