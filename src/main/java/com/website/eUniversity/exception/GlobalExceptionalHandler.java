package com.website.eUniversity.exception;

import com.website.eUniversity.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<BaseResponse> refreshTokenNotFoundExceptionCatch(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse<>().error(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<BaseResponse> refreshTokenExpiredExceptionCatch(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse<>().error(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> notFoundExceptionCatch(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>().error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> exceptionCatch(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>().error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}