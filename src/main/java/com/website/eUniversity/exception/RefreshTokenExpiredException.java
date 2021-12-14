package com.website.eUniversity.exception;

public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException() {
        super("Token is expired");
    }
}
