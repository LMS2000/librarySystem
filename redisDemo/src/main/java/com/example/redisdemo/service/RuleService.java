package com.example.redisdemo.service;

import com.example.redisdemo.domain.Rule;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface RuleService {
    List<Rule> selectRuleAll();
    int deleteRule(Long rId) throws DataNoFoundException, MethodFailureRunException;
    int updateRule(Rule rule) throws DataNoFoundException, MethodFailureRunException;
    int insertRule(Rule rule) throws MethodFailureRunException;

}
