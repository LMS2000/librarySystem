package com.example.redisdemo.utils;

import java.io.Serializable;

public class ErrorResult extends RespJson implements Serializable {
     private Exception errors;

    ErrorResult(Integer code, String message, Exception errors) {
        super.code = code;
        this.message = message;
        this.errors = errors;
    }
    ErrorResult(){}
    public ErrorResult(ResultEnum resultEnum, Exception ex)
    {
        super.code=resultEnum.getCode();
        super.message=resultEnum.getMessage();
        this.errors=ex;
    }
    public Exception getErrors() {
        return errors;
    }

    public void setErrors(Exception errors) {
        this.errors = errors;
    }
}
