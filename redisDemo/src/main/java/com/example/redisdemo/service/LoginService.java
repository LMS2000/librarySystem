package com.example.redisdemo.service;


import com.example.redisdemo.exception.DataNoFoundException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    int  updatePassword(String token,String oldPassword,String newPassword) throws Exception;


}
