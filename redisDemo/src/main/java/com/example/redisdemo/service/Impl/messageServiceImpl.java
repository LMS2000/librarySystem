package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.Message;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.MessageMapper;
import com.example.redisdemo.service.MessageService;
import com.example.redisdemo.utils.redisCacheUtil;
import com.sun.org.apache.regexp.internal.RE;
import jdk.internal.dynalink.linker.MethodHandleTransformer;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "message")
public class messageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Override
    @Cacheable
    public List<Message> selectMessageList() {
        return messageMapper.selectMessage();
    }
    @Override
    public int insertMessage(Message message) throws MethodFailureRunException {
        int row = messageMapper.insertMessage(message);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Message.class);
        }
        return row;
    }
}
