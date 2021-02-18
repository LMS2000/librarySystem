package com.example.redisdemo.utils;


import java.io.Serializable;

public class RespJson implements Serializable {
    protected Integer code;
    protected String message;
    private Object data;

    public RespJson(ResultEnum resultEnum, Object data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }
    public RespJson(){}

    @Override
    public String toString() {
        return "RespJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public void setResultCode(ResultEnum resultCode)
    {
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
    }
    public static RespJson success(Object data)
    {
        RespJson respJson = new RespJson() ;
        respJson.setResultCode(ResultEnum.SUCCESS);
        respJson.setData(data);
        return respJson;
    }
    public  static RespJson success()
    {
        RespJson respJson= new RespJson() ;
        respJson.setResultCode(ResultEnum.SUCCESS);
        return respJson;
    }
    public  static RespJson failure(ResultEnum resultEnum )
    {
        RespJson respJson=new RespJson() ;
        respJson.setResultCode(resultEnum);
        return respJson;
    }
    public static RespJson failure(ResultEnum resultEnum ,Object data)
    {
        RespJson respJson =new RespJson() ;
        respJson.setData(data);
        respJson.setResultCode(resultEnum);
        return respJson;
    }
    public static RespJson failure(Integer code ,String message,Exception ex)
    {
        RespJson respJson =new RespJson() ;
        respJson.setCode(code);
        respJson.setMessage(message);
        respJson.setData(ex);
        return respJson;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
