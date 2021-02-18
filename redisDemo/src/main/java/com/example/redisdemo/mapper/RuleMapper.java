package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.Rule;

import java.util.List;

public interface RuleMapper {
    List<Rule> selectRule(Rule rule );
    int updateRule(Rule rule);
    int insertRule(Rule rule);
    int deleteRule(Long rId);
    List<Rule> selectRuleAll();
    Rule selectRuleById(Long rId);
}
