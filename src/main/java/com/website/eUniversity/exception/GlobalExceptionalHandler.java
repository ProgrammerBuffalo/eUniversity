package com.website.eUniversity.exception;

import com.website.eUniversity.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> exceptionCatch(Exception ex) {
        return new BaseResponse<>().error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

}