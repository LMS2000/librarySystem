package com.example.redisdemo.utils;

import com.example.redisdemo.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class redisCacheUtil {
    public static<T> void clearCache(StringRedisTemplate stringRedisTemplate ,Class<T> tClass){
     if(Announcement.class.equals(tClass))
     {
         stringRedisTemplate.delete("announcement::SimpleKey []");
     }
     else if(Book.class.equals(tClass))
     {
         stringRedisTemplate.delete("book::SimpleKey []");
     }
     else if(BookSort.class.equals(tClass))
     {
         stringRedisTemplate.delete("bookSort::SimpleKey []");
     }
     else if(BorrowBook.class.equals(tClass))
     {
         stringRedisTemplate.delete("borrowBook::selectBorrowBookReturned");
         stringRedisTemplate.delete("borrowBook::selectBorrowBookNotReturned");
         stringRedisTemplate.delete("borrowBook::selectBorrowBookByUserReturned");
         stringRedisTemplate.delete("borrowBook::selectBorrowBookByUserNotReturned");
     }
     else if(BorrowCard.class.equals(tClass))
     {
         stringRedisTemplate.delete("borrowCard::SimpleKey []");
     }
     else if(Manager.class.equals(tClass))
     {
         stringRedisTemplate.delete("manager::SimpleKey []");
     }
     else if(Rule.class.equals(tClass))
     {
         stringRedisTemplate.delete("rule::SimpleKey []");
     }else if(Message.class.equals(tClass))
     {
         stringRedisTemplate.delete("message::SimpleKey []");
     }
    }
    public static void deleteCache(StringRedisTemplate stringRedisTemplate ,String key)
    {
            stringRedisTemplate.delete(key);
    }
    public static<T> String KeyGenerator(Long id,Class<T> clazz){
        StringBuilder stringBuilder =new StringBuilder() ;
        if(Announcement.class.equals(clazz))
        {
            stringBuilder.append("announcement::"+id.toString());
        }
        else if(Book.class.equals(clazz))
        {
            stringBuilder.append("book::"+id.toString());
        }
        else if(BorrowBook.class.equals(clazz))
        {
            stringBuilder.append("borrowBook::"+id.toString());
        }
        else if(BorrowCard.class.equals(clazz))
        {
            stringBuilder.append("borrowCard::"+id.toString());
        }
        else if(BookSort.class.equals(clazz))
        {
            stringBuilder.append("bookSort::"+id.toString());
        }
        else if(Manager.class.equals(clazz))
        {
            stringBuilder.append("manager::"+id.toString());
        }
        else if(Rule.class.equals(clazz))
        {
            stringBuilder.append("rule::"+id.toString());
        }
        return stringBuilder.toString();
    }
    public static void setCache(RedisTemplate<Object,Announcement> redisTemplate,String key,Announcement announcement)
    {
        redisTemplate.opsForValue().set(key,announcement,8000, TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,Book> redisTemplate,String key,Book book)
    {
        redisTemplate.opsForValue().set(key,book,8000,TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,BorrowBook> redisTemplate,String key,BorrowBook borrowBook )
    {
        redisTemplate.opsForValue().set(key,borrowBook,8000,TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,BorrowCard> redisTemplate,String key,BorrowCard borrowCard)
    {
        redisTemplate.opsForValue().set(key,borrowCard,8000,TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,BookSort> redisTemplate,String key,BookSort bookSort)
    {
        redisTemplate.opsForValue().set(key,bookSort,8000,TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,Manager> redisTemplate,String key,Manager manager)
    {
        redisTemplate.opsForValue().set(key,manager,8000, TimeUnit.SECONDS);
    }
    public static void setCache(RedisTemplate<Object,Rule> redisTemplate,String key,Rule rule)
    {
        redisTemplate.opsForValue().set(key,rule,8000,TimeUnit.SECONDS);
    }
    public static Announcement getAnnouncementCache(RedisTemplate<Object,Announcement> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static Book getBookCache(RedisTemplate<Object,Book> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static BorrowBook getBorrowBookCache(RedisTemplate<Object,BorrowBook> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static BookSort getBookSortCache(RedisTemplate<Object,BookSort> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static BorrowCard getBorrowCardCache(RedisTemplate<Object,BorrowCard> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static Manager getManagerCache(RedisTemplate<Object,Manager> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public static Rule getRuleCache(RedisTemplate<Object,Rule> redisTemplate,String key)
    {
        return redisTemplate.opsForValue().get(key);
    }







}
