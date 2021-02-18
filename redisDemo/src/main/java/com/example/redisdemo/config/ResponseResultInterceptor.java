package com.example.redisdemo.config;

import com.example.redisdemo.annotation.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    private  static  final  String RESPONSE_ANNOTATION_ANN="RESPONSE-ANNOTATION-ANN";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

     if(handler instanceof HandlerMethod)
     {
         final  HandlerMethod handlerMethod =(HandlerMethod) handler;
         final  Class<?> clazz =handlerMethod.getClass();
         final Method method=handlerMethod.getMethod();
         if(clazz.isAnnotationPresent(ResponseResult.class))
         {
             request.setAttribute(RESPONSE_ANNOTATION_ANN,clazz.getAnnotation(ResponseResult.class));
         }
         else if(method.isAnnotationPresent(ResponseResult.class))
         {
             request.setAttribute(RESPONSE_ANNOTATION_ANN,method.getDeclaredAnnotation(ResponseResult.class));
         }
     }
        return true;
    }
}
