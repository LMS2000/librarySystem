package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.LoginInTable;
import com.example.redisdemo.domain.Manager;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.LoginMapper;
import com.example.redisdemo.mapper.ManagerMapper;
import com.example.redisdemo.service.ManagerService;
import com.example.redisdemo.utils.JWTokenUtil;
import com.example.redisdemo.utils.redisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "manager")
public class managerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper  managerMapper ;
    @Autowired
    private LoginMapper loginMapper ;
    @Autowired
    private RedisTemplate<Object,Manager> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Override
    public int updateManager(Manager manager) throws DataNoFoundException, MethodFailureRunException {

        Long aLong = manager.getmId();
        if(aLong==null){ throw new DataNoFoundException("mId为空！"); }
        if(!checkManagerIsExist(aLong)){ throw new DataNoFoundException("要修改的数据不存在！");}
        int row= managerMapper.updateManager(manager );
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Manager.class);
        }
        return row;
    }

    @Override
    @Cacheable
    public List<Manager> selectManagerAll() {
        return managerMapper.selectManagerAll();
    }

    @Override
    public int insertManager(Manager manager) throws MethodFailureRunException {
            LoginInTable loginInTable =new LoginInTable();
            BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder() ;
            loginInTable.setPassword(bCryptPasswordEncoder.encode("123456"));
            loginInTable.setUsername(JWTokenUtil.CreateJTI().substring(0,6).toLowerCase());
            loginInTable.setState(1);
            loginInTable.setRole("ROLE_USER");
            loginMapper.insertAccount(loginInTable);
            LoginInTable loginInTable1 = loginMapper.queryAdmin(loginInTable);
            manager.setLoginAccountId(loginInTable1.getuId());
           int row=  managerMapper.insertManager(manager);
           if(row>=0){
               redisCacheUtil.clearCache(stringRedisTemplate,Manager.class);
           }
           return row;
    }

    @Override
    @CacheEvict(key = "#mId")
    public int deleteManager(Long mId) throws DataNoFoundException {
        if(!checkManagerIsExist(mId)){throw new DataNoFoundException("要删除的数据不存在！");}
        Manager manager = managerMapper.selectManagerById(mId);
        if(manager==null){throw new DataNoFoundException("找不到指定账号");}
        Long id= manager.getLoginAccountId();
        int row = managerMapper.deleteManager(mId);
        if(row>=0){
            loginMapper.deleteLoginInTableById(id);
            redisCacheUtil.clearCache(stringRedisTemplate,Manager.class);
        }
        return row;
    }
    public Boolean checkManagerIsExist(Long mId){
        String key =redisCacheUtil.KeyGenerator(mId,Manager.class);
        Manager manager=redisCacheUtil.getManagerCache(redisTemplate,key);
        if(manager==null)
        {
             manager = managerMapper.selectManagerById(mId);
             redisCacheUtil.setCache(redisTemplate,key,manager);
        }
      return manager==null?false:true;
    }
}
