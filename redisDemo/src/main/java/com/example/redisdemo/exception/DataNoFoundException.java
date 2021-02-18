package com.example.redisdemo.exception;

public class DataNoFoundException extends Exception {
    private String message;
    public DataNoFoundException(String message) {
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
