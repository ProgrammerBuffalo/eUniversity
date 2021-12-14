package com.website.eUniversity.exception;

public class RefreshTokenNotFoundException extends Exception {

    public RefreshTokenNotFoundException() {
        super("Token not found exception");
    }
}
