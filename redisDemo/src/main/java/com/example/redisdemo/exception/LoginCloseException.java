package com.example.redisdemo.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginCloseException extends AuthenticationException {
    private String message;
    public LoginCloseException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
