package com.example.redisdemo.config;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class myKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {

        return method.getName();
    }
}
