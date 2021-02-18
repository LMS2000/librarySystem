package com.example.redisdemo.config;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.utils.JsonUtils;
import com.example.redisdemo.utils.RespJson;
import com.example.redisdemo.utils.ResultEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    private static  final String RESPONSE_ANNOTATION_ANN="RESPONSE-ANNOTATION-ANN";
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        ResponseResult attribute = (ResponseResult)request.getAttribute(RESPONSE_ANNOTATION_ANN);
        return attribute==null? false:true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String)
        {
            String body=o.toString();
            if(body.startsWith("ex"))
            {
                String ex = body.replace("ex", "");
                RespJson respJson =JsonUtils.toBean(ex,RespJson.class);
                return respJson ;
            }
        }
        return RespJson.success(o);
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exception(Exception ex, WebRequest request)throws Exception{
      if(ex instanceof BadCredentialsException)
      {
          return "ex"+JsonUtils.toString(RespJson.failure(ResultEnum.USER_LOGIN_ERROR)) ;
      }
      else if(ex instanceof UsernameNotFoundException)
      {
          return "ex"+JsonUtils.toString(RespJson.failure(ResultEnum.USER_NOT_EXIST));
      }
      else if(ex instanceof NullPointerException)
      {
          return "ex"+JsonUtils.toString(RespJson.failure(ResultEnum.PARAM_IS_BLANK));
      }else if(ex instanceof DataNoFoundException)
      {
          return "ex"+JsonUtils.toString(RespJson.failure(ResultEnum.PARAM_NO_FOUND,ex.getMessage()));
      }
      else if(ex instanceof MethodFailureRunException)
      {
          return "ex"+JsonUtils.toString(RespJson.failure(ResultEnum.DATA_UPDATE_FAILURE,ex.getMessage()));
      }

      //否则返回默认的响应值
      return ex.toString();
    }
}
