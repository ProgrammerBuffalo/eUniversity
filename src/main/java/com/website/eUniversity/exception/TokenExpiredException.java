package com.website.eUniversity.exception;

public class TokenExpiredException extends Exception {

    public TokenExpiredException() {
        super("Token is expired");
    }
}
