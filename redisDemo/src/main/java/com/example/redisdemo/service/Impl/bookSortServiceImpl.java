package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.Book;
import com.example.redisdemo.domain.BookSort;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.BookMapper;
import com.example.redisdemo.mapper.BookSortMapper;
import com.example.redisdemo.service.BookService;
import com.example.redisdemo.service.BookSortService;
import com.example.redisdemo.utils.redisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "bookSort")
public class bookSortServiceImpl implements BookSortService {
   @Autowired
    private BookSortMapper bookSortMapper;
   @Autowired
    private BookMapper bookMapper ;
   @Autowired
   private RedisTemplate<Object,BookSort> redisTemplate;
  @Autowired
   private StringRedisTemplate stringRedisTemplate ;
    @Override
    @Cacheable
    public List<BookSort> selectBookSortAll() {
        return bookSortMapper.selectBookSortAll();
    }

    @Override
    @CacheEvict(key="#bsId")
    public int deleteBookSort(Long bsId) throws DataNoFoundException {
        if(!checkBookSortIsExist(bsId)){
            throw new DataNoFoundException("数据不存在");
        }else if(checkBookSortIsUsed(bsId))
        {
            throw new DataNoFoundException("字段被使用中");
        }
        redisCacheUtil.clearCache(stringRedisTemplate,BookSort.class);
        return bookSortMapper.deleteBookSort(bsId);
    }

    @Override
    public int updateBookSort(BookSort bookSort) throws DataNoFoundException, MethodFailureRunException {
        if(!checkBookSortIsExist(bookSort.getBsId())){ throw new DataNoFoundException("未指定数据");}
        int i =bookSortMapper.updateBookSort(bookSort);
        if(i<=0){ throw new MethodFailureRunException("数据修改失败！");}
        redisCacheUtil.clearCache(stringRedisTemplate,BookSort.class);
        return i;
    }

    @Override
    public int insertBookSort(BookSort bookSort) throws MethodFailureRunException {
        int i = bookSortMapper.insertBookSort(bookSort);
        if(i<=0){throw new MethodFailureRunException("数据插入失败！");}
        redisCacheUtil.clearCache(stringRedisTemplate,BookSort.class);
        return i;
    }

    public Boolean checkBookSortIsUsed(Long bsId)
    {
        Book book = new Book();
        book.setSortId(bsId);
        List<Book> books =bookMapper.selectBook(book);
        return books.isEmpty()?false:true;
    }
    public Boolean checkBookSortIsExist(Long bsId)
    {
        String key =redisCacheUtil.KeyGenerator(bsId,BookSort.class);
        BookSort bookSort =redisCacheUtil.getBookSortCache(redisTemplate,key);
        if(bookSort==null)
        {
             bookSort = bookSortMapper.selectBookSortById(bsId);
             redisCacheUtil.setCache(redisTemplate,key,bookSort);
        }
        return bookSort==null?false:true;
    }


}
