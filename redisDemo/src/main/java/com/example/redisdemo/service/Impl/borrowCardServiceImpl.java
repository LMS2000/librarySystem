package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.domain.LoginInTable;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.BorrowCardMapper;
import com.example.redisdemo.mapper.LoginMapper;
import com.example.redisdemo.service.BorrowCardService;
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
@CacheConfig(cacheNames = "borrowCard")
public class borrowCardServiceImpl  implements BorrowCardService {
    @Autowired
    private BorrowCardMapper borrowCardMapper ;
    @Autowired
    private LoginMapper loginMapper ;
    @Autowired
    private RedisTemplate<Object,BorrowCard> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Override
    @Cacheable
    public List<BorrowCard> selectBorrowCard() {
        return borrowCardMapper.selectBorrowCardAll();
    }

    @Override
    public int insertBorrowCard(BorrowCard borrowCard) {
        LoginInTable loginInTable =new LoginInTable();
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder() ;
        loginInTable.setPassword(bCryptPasswordEncoder.encode("123456"));
        loginInTable.setUsername(JWTokenUtil.CreateJTI().substring(0,6).toLowerCase());
        loginInTable.setState(1);
        loginInTable.setRole("ROLE_USER");
        loginMapper.insertAccount(loginInTable);
        LoginInTable loginInTable1 = loginMapper.queryAdmin(loginInTable);
        borrowCard.setLoginAccountId(loginInTable1.getuId());
        int row=  borrowCardMapper.insertBorrowCard(borrowCard );
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowCard.class);
        }

        return row;
    }
    public Boolean checkBorrowCardIsExist(Long bcId)
    {
        String key =redisCacheUtil.KeyGenerator(bcId,BorrowCard.class);
        BorrowCard borrowCard  =redisCacheUtil.getBorrowCardCache(redisTemplate,key);
        if(borrowCard==null)
        {
            borrowCard = borrowCardMapper.selectBorrowCardById(bcId);
            redisCacheUtil.setCache(redisTemplate,key,borrowCard);
        }
        return borrowCard==null?false:true;
    }
    @Override
    @CacheEvict(key="#bcId")
    public int deleteBorrowCard(Long bcId) throws DataNoFoundException {
        if(!checkBorrowCardIsExist(bcId)){ throw new DataNoFoundException("数据不存在"); }
        BorrowCard borrowCard = borrowCardMapper.selectBorrowCardById(bcId);
        Long loginAccountId = borrowCard.getLoginAccountId();
        if(loginAccountId==null){throw new DataNoFoundException("无对应账号");}
        loginMapper.deleteLoginInTableById(loginAccountId);
        int row =  borrowCardMapper.deleteBorrowCard(bcId);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowCard.class);
        }
        return row;
    }
    @Override
    public int updateBorrowCard(BorrowCard borrowCard) throws DataNoFoundException {
        Long bcId = borrowCard.getBcId();
        if(bcId==null){ throw new DataNoFoundException("修改的id不能为空");}
        if(!checkBorrowCardIsExist(bcId)){ throw new DataNoFoundException("借阅证id不存在");}
        int row = borrowCardMapper.updateBorrowCard(borrowCard );
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowCard.class);
        }
        return row;
    }
}
