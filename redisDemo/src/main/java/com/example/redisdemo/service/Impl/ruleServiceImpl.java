package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.domain.Rule;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.BorrowCardMapper;
import com.example.redisdemo.mapper.RuleMapper;
import com.example.redisdemo.service.RuleService;
import com.example.redisdemo.utils.redisCacheUtil;
import com.sun.media.jfxmedia.MediaException;
import jdk.internal.dynalink.linker.MethodHandleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "rule")
public class ruleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private BorrowCardMapper borrowCardMapper ;
    @Autowired
    private RedisTemplate<Object,Rule> redisTemplate;
    @Autowired
    private StringRedisTemplate  stringRedisTemplate;
    @Override
    @Cacheable
    public List<Rule> selectRuleAll() {
        return ruleMapper.selectRuleAll();
    }

    @Override
    @CacheEvict(key = "#rId")
    public int deleteRule(Long rId) throws DataNoFoundException, MethodFailureRunException {
        if(!checkRuleIsExist(rId)){ throw new DataNoFoundException("数据不存在");}
        BorrowCard borrowCard =new BorrowCard() ;
        borrowCard.setRuleId(rId );
        List<BorrowCard> borrowCards = borrowCardMapper.selectBorrowCard(borrowCard);
        if(!borrowCards.isEmpty()){ throw new DataNoFoundException("该规则被使用中");}
        int row = ruleMapper.deleteRule(rId);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Rule.class);
        }
        return row;
    }
    @Override
    public int updateRule(Rule rule) throws DataNoFoundException, MethodFailureRunException {
        if(!checkRuleIsExist(rule.getrId())){ throw new DataNoFoundException("数据不存在！");}
        BorrowCard borrowCard =new BorrowCard() ;
        borrowCard.setRuleId(rule.getrId());
        List<BorrowCard> borrowCards = borrowCardMapper.selectBorrowCard(borrowCard);
        if(!borrowCards.isEmpty()){ throw new DataNoFoundException("该规则被使用中");}
        int row  =ruleMapper.updateRule(rule);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Rule.class);
        }
        return row;
    }
    @Override
    public int insertRule(Rule rule) throws MethodFailureRunException {
        int  row = ruleMapper.insertRule(rule);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Rule.class);
        }
        return row;
    }
    public Boolean checkRuleIsExist(Long rId)
    {
        String key =redisCacheUtil.KeyGenerator(rId,Rule.class);
        Rule  rule =redisCacheUtil.getRuleCache(redisTemplate,key);
        if(rule==null)
        {
             rule=  ruleMapper.selectRuleById(rId);
             redisCacheUtil.setCache(redisTemplate,key,rule);
        }
       return rule==null?false:true;
    }
}
