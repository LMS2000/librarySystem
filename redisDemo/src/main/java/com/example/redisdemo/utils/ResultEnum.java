package com.example.redisdemo.utils;

public enum ResultEnum {
    SUCCESS(1,"成功"),
    PARAM_IS_INVALID(1001,"参数无效"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_IS_COMPLETE(1003,"参数缺失"),
    PARAM_TYPE_BIND_ERROR(1004,"参数类型错误"),
    PARAM_NO_FOUND(1005,"找不到指定的数据或数据不存在"),
    USER_NOT_LOGGED_IN(2001,"用户未登录，访问路径需要权限！"),
    USER_LOGIN_ERROR(2002,"用户名或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"此账号已被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),
    DATA_UPDATE_FAILURE(3002,"数据修改失败");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
