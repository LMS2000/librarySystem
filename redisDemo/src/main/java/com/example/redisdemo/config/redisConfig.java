package com.example.redisdemo.config;


import com.example.redisdemo.domain.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;

@Configuration
public class redisConfig {


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }
    @Bean
    public RedisTemplate<Object, Announcement> AnnouncementRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Announcement> template = new RedisTemplate<Object, Announcement>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<Announcement> ser = new Jackson2JsonRedisSerializer<Announcement>(Announcement.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, Book> BookRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Book> template = new RedisTemplate<Object, Book>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<Book> ser = new Jackson2JsonRedisSerializer<Book>(Book.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, BookSort> BookSortRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, BookSort> template = new RedisTemplate<Object, BookSort>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<BookSort> ser = new Jackson2JsonRedisSerializer<BookSort>(BookSort.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, BorrowBook> BorrowBookRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, BorrowBook> template = new RedisTemplate<Object, BorrowBook>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<BorrowBook> ser = new Jackson2JsonRedisSerializer<BorrowBook>(BorrowBook.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, BorrowCard> BorrowCardRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, BorrowCard> template = new RedisTemplate<Object, BorrowCard>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<BorrowCard> ser = new Jackson2JsonRedisSerializer<BorrowCard>(BorrowCard.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, Manager> ManagerRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Manager> template = new RedisTemplate<Object, Manager>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<Manager> ser = new Jackson2JsonRedisSerializer<Manager>(Manager.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, Message> MessageRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Message> template = new RedisTemplate<Object, Message>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<Message> ser = new Jackson2JsonRedisSerializer<Message>(Message.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, Rule> RuleRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Rule> template = new RedisTemplate<Object, Rule>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<Rule> ser = new Jackson2JsonRedisSerializer<Rule>(Rule.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean
    public RedisTemplate<Object, List<BorrowBook>> ListRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,  List<BorrowBook>> template = new RedisTemplate<Object,  List<BorrowBook>>();
        template.setConnectionFactory(redisConnectionFactory);
//        默认是的用jdk序列化的，需要改成json
        Jackson2JsonRedisSerializer<BorrowBook> ser
                = new Jackson2JsonRedisSerializer<BorrowBook>( BorrowBook.class);
        template.setDefaultSerializer(ser);
        RedisSerializer redisSerializer = new    StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        return template;
    }
    @Bean("myKeyGenerator")
    public myKeyGenerator getMyKeyGenerator()
    {
        return new myKeyGenerator();
    }
}
