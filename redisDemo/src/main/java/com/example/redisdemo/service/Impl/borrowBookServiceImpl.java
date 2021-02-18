package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.*;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.BookMapper;
import com.example.redisdemo.mapper.BookSortMapper;
import com.example.redisdemo.mapper.BorrowBookMapper;
import com.example.redisdemo.mapper.BorrowCardMapper;
import com.example.redisdemo.service.BorrowBookService;
import com.example.redisdemo.utils.JWTokenUtil;
import com.example.redisdemo.utils.JsonUtils;
import com.example.redisdemo.utils.RsaUtils;
import com.example.redisdemo.utils.redisCacheUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "borrowBook")
public class borrowBookServiceImpl implements BorrowBookService {
    @Autowired
    private BorrowBookMapper borrowBookMapper ;
    @Autowired
    private BookMapper bookMapper ;
    @Autowired
    private BorrowCardMapper borrowCardMapper ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Autowired
    private RedisTemplate<Object,BorrowBook> redisTemplate;
    @Autowired
    private RedisTemplate<Object,BorrowCard> borrowCardRedisTemplate;
    @Override
    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<BorrowBook> selectBorrowBookByUserReturned(String token) throws Exception {
        Long bcId=getBcId(token).getBcId();
        List<BorrowBook> borrowBooks=borrowBookMapper.selectBorrowBookByUser(bcId);
        return borrowBooks ;
    }
    public BorrowCard getBcId(String token) throws Exception {
        Payload<UserPojo> userPojoPayload =
                JWTokenUtil
                        .getUserInfoFromToken(token , RsaUtils.getPublicKey(stringRedisTemplate.opsForValue().get("publicKey").getBytes())  , UserPojo.class);
        UserPojo userInfo = userPojoPayload.getUserInfo();
        Long id = userInfo.getId();
        BorrowCard borrowCardCopy =borrowCardRedisTemplate.opsForValue().get("borrowCard::current");
        if(borrowCardCopy==null||borrowCardCopy.getLoginAccountId()!=id)
        {
            BorrowCard  borrowCard =new BorrowCard();
            borrowCard.setLoginAccountId(id);
            List<BorrowCard> borrowCards = borrowCardMapper.selectBorrowCard(borrowCard);
            borrowCardRedisTemplate.opsForValue().set("borrowCard::current",borrowCards.get(0),8000,TimeUnit.SECONDS);
            redisCacheUtil.deleteCache(stringRedisTemplate,"borrowBook::selectBorrowBookByUserNotReturned");
            redisCacheUtil.deleteCache(stringRedisTemplate,"borrowBook::userReturned");
            return borrowCards.get(0);
        }
        return borrowCardCopy;
    }
    @Override
    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<BorrowBook> selectBorrowBookByUserNotReturned(String token) throws Exception {
      return borrowBookMapper.selectBorrowBookByUserV2(getBcId(token).getBcId());
    }

    @Override
    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<BorrowBook> selectBorrowBookReturned() {
        return borrowBookMapper.selectBorrowBookReturned();
    }

    @Override
    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<BorrowBook> selectBorrowBookNotReturned() {
        return borrowBookMapper.selectBorrowBookReturned();
    }

    @Override
    public int updateBorrowBook(BorrowBook borrowBook) throws DataNoFoundException, MethodFailureRunException {
        if(!checkBorrowBookIsExist(borrowBook.getBbId()))
        {
            throw new DataNoFoundException("数据列不存在");
        }
        else if(!checkBookIsExist(borrowBook.getBookId()))
        {
            throw new DataNoFoundException("图书不存在");
        }
        else if(!checkBorrowCardIsExist(borrowBook.getCardId()))
        {
            throw new DataNoFoundException("借阅证不存在");
        }
        int row =borrowBookMapper.updateBorrowBook(borrowBook);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowBook.class);
        }
        return row;
    }

    @Override
    @CacheEvict(key="#bbId")
    public int deleteBorrowBook(Long bbId) throws DataNoFoundException, MethodFailureRunException {
        if(!checkBorrowBookIsExist(bbId)){throw new DataNoFoundException("数据列不存在");}
        int row =borrowBookMapper.deleteBorrowBook(bbId);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowBook.class);
        }
        return row;
    }

    @Override
    public int insertBorrowBook(BorrowBook borrowBook) throws DataNoFoundException, MethodFailureRunException {
       if(!checkBookIsExist(borrowBook.getBookId()))
        {
            throw new DataNoFoundException("图书不存在");
        }
        else if(!checkBorrowCardIsExist(borrowBook.getCardId()))
        {
            throw new DataNoFoundException("借阅证不存在");
        }
        int row  =borrowBookMapper.insertBorrowBook(borrowBook);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,BorrowBook.class);
        }
        return row;
    }

    public Boolean checkBorrowBookIsExist(Long bbId)
    {
        String key =redisCacheUtil.KeyGenerator(bbId,BorrowBook.class);
        BorrowBook borrowBook=redisCacheUtil.getBorrowBookCache(redisTemplate,key);
        if(borrowBook==null)
        {
             borrowBook = borrowBookMapper.selectBorrowBookById(bbId);
             redisCacheUtil.setCache(redisTemplate,key,borrowBook);
        }
       return borrowBook==null?false:true;
    }
    public Boolean checkBookIsExist(Long bId)
    {
     Book book =  bookMapper.selectBookById(bId);
     return book==null?false:true;
    }
    public Boolean checkBorrowCardIsExist(Long bcId)
    {
      BorrowCard borrowCard =  borrowCardMapper.selectBorrowCardById(bcId);
      return borrowCard==null?false:true;
    }
}
