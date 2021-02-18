package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.Rule;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class ruleController {
    @Autowired
    private RuleService ruleService ;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<Rule> getRuleList()
    {
       return ruleService.selectRuleAll();
    }
    @PostMapping
    @ResponseResult
    public void insertRule(Rule rule) throws MethodFailureRunException {
        int i = ruleService.insertRule(rule);
        if(i<=0){throw new MethodFailureRunException("数据插入失败！");}
    }
    @PutMapping
    @ResponseResult
    public void updateRule(Rule rule) throws MethodFailureRunException, DataNoFoundException {
        int i = ruleService.updateRule(rule);
        if(i<=0){throw new MethodFailureRunException("数据修改失败！");}
    }
    @DeleteMapping("{rId}")
    @ResponseResult
    public void deleteRule(@PathVariable Long rId) throws MethodFailureRunException, DataNoFoundException {
        int i = ruleService.deleteRule(rId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }





}
