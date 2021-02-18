package com.example.redisdemo.service;

import com.example.redisdemo.domain.Manager;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface ManagerService {
    int updateManager(Manager manager) throws DataNoFoundException, MethodFailureRunException;
    List<Manager> selectManagerAll();
    int insertManager(Manager manager) throws MethodFailureRunException;
    int deleteManager(Long mId) throws DataNoFoundException;
}
