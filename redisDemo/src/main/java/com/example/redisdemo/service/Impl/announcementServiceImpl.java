package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.Announcement;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.mapper.AnnouncementMapper;
import com.example.redisdemo.service.AnnouncementService;
import com.example.redisdemo.utils.redisCacheUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "announcement")
public class announcementServiceImpl implements AnnouncementService{
    @Autowired
    private AnnouncementMapper announcementMapper ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Autowired
    private RedisTemplate<Object,Announcement> redisTemplate ;
    @Override
    @Cacheable
    public List<Announcement> selectAnnouncementAll() {
        return announcementMapper.selectAnnouncementAll();
    }

    @Override
    public int insertAnnouncement(Announcement announcement) {
     int row =     announcementMapper.insertAnnouncement(announcement);
          if(row>=0){
              redisCacheUtil.clearCache(stringRedisTemplate,Announcement.class);
          }
        return row;
    }
    public Boolean checkAnnouncement(Long aId){
        String key=redisCacheUtil.KeyGenerator(aId,Announcement.class);
        Announcement announcement=redisCacheUtil.getAnnouncementCache(redisTemplate,key);
        if(announcement==null)
        {
             announcement =announcementMapper.selectAnnouncementById(aId);
             redisCacheUtil.setCache(redisTemplate,key,announcement);
        }
        return announcement==null?false:true;
    }

    @Override
    @CacheEvict(key = "#aId")
    public int deleteAnnouncement(Long aId) throws DataNoFoundException {
       if(!checkAnnouncement(aId )){ throw new DataNoFoundException("要删除的数据不存在");}
       int row =announcementMapper.deleteAnnouncement(aId);
       if(row>=0)
       {
           redisCacheUtil.clearCache(stringRedisTemplate,Announcement.class);
       }
        return row;
    }
    @Override
    public int updateAnnouncement(Announcement announcement) throws DataNoFoundException{
        if(!checkAnnouncement(announcement.getaId())){ throw new DataNoFoundException("要修改的数据不存在");}
        int row = announcementMapper.updateAnnouncement(announcement);
        if(row>=0){
            redisCacheUtil.clearCache(stringRedisTemplate,Announcement.class);
        }
        return row;
    }
}
