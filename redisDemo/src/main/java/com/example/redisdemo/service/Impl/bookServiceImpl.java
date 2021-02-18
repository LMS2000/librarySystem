package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.Book;
import com.example.redisdemo.domain.BookSort;
import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.domain.Library;
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

import javax.xml.crypto.Data;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "book")
public class bookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper ;
    @Autowired
    private BookSortMapper bookSortMapper ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Autowired
    private RedisTemplate<Object,Book> redisTemplate;
    @Override
    @Cacheable
    public List<Book> selectBookAll() {
        return bookMapper.selectBookAll();
    }

    @Override
    public int insertBook(Book book) throws DataNoFoundException, MethodFailureRunException {
        if(!checkLibraryIsExist(book .getLibraryId())){
            throw new DataNoFoundException("图书馆不存在！");
        }else if(!checkBookSortIsExist(book.getSortId()))
        {
            throw new DataNoFoundException("图书分类不存在");
        }
        int row = bookMapper.insertBook(book);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Book.class);
        }
        return row;
    }


    public Boolean checkBookIsExist(Long bId){
        String key=redisCacheUtil.KeyGenerator(bId,Book.class);
        Book book =redisCacheUtil.getBookCache(redisTemplate,key);
        if(book==null){
             book= bookMapper.selectBookById(bId);
              redisCacheUtil.setCache(redisTemplate,key,book);
        }
       return book==null?false:true;
    }
    public Boolean checkLibraryIsExist(Long lId)
    {
        Library library = bookMapper.selectLibraryById(lId);
        return library==null?false:true;
    }
    public Boolean checkBookSortIsExist(Long bsId)
    {
      BookSort bookSort =  bookSortMapper.selectBookSortById(bsId);
     return bookSort==null?false:true;
    }
    @Override
    public int updateBook(Book book) throws DataNoFoundException, MethodFailureRunException {
        if(!checkBookIsExist(book.getbId()))
        {
            throw new DataNoFoundException("要修改的数据不存在");
        }
        else if(!checkBookSortIsExist(book.getSortId()))
        {
            throw new DataNoFoundException("图书分类不存在");
        }
        else if(!checkLibraryIsExist(book.getLibraryId()))
        {
            throw new DataNoFoundException("图书馆不存在");
        }
        else if(!checkBookIsbBorrow(book.getbId()))
        {
            throw new DataNoFoundException("书籍已经借出");
        }
        int row  =bookMapper.updateBook(book);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Book.class);
        }

        return row;
    }
    public Boolean checkBookIsbBorrow(Long bId)
    {
        Book book = bookMapper.selectBookById(bId);
        return book.getBorrowState()==0?false:true;
    }
    @Override
    @CacheEvict(key="#bId")
    public int deleteBook(Long bId) throws DataNoFoundException {
        if(!checkBookIsExist(bId)){ throw new DataNoFoundException("要删除的数据不存在！");}
        if(!checkBookIsbBorrow(bId)){ throw new DataNoFoundException("书籍已经借出");}
        int row =bookMapper.deleteBook(bId);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Book.class);
        }
        return row;
    }
}
