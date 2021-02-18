package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.Manager;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class managerController {
    @Autowired
    private ManagerService managerService ;
    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<Manager> getManagerList()
    {
        return managerService.selectManagerAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseResult
    public void insertManager(Manager manager) throws MethodFailureRunException {
        int i = managerService.insertManager(manager);
        if(i<=0){throw new MethodFailureRunException("插入数据失败！");}
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseResult
    public void updateManager(Manager manager) throws DataNoFoundException, MethodFailureRunException {
        int i = managerService.updateManager(manager);
        if(i<=0){throw new MethodFailureRunException("数据修改失败!");}
    }
    @RequestMapping(value = "/{mId}",method = RequestMethod.DELETE)
    @ResponseResult
    public void deleteManager(@PathVariable Long mId) throws DataNoFoundException, MethodFailureRunException {
        int i = managerService.deleteManager(mId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }
}
