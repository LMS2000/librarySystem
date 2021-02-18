package com.example.redisdemo;


import com.example.redisdemo.domain.*;
import com.example.redisdemo.mapper.*;
import com.example.redisdemo.service.*;
import com.example.redisdemo.utils.JWTokenUtil;
import com.example.redisdemo.utils.JsonUtils;
import com.example.redisdemo.utils.RsaUtils;
import com.example.redisdemo.utils.redisCacheUtil;
import io.lettuce.core.ScriptOutputType;
import netscape.javascript.JSUtil;
import org.assertj.core.internal.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.PublicKey;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest
class RedisdemoApplicationTests {

    private String token ="BearereyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoie1wiaWRcIjoxLFwidXNlcm5hbWVcIjpcIjE4MDUwMTAyMDJcIixcInBhc3N3b3JkXCI6bnVsbCxcInN0YXRlXCI6bnVsbCxcInJvbGVzXCI6W3tcImlkXCI6bnVsbCxcInJvbGVOYW1lXCI6XCJST0xFX0dVRVNUXCIsXCJyb2xlRGVzY1wiOm51bGx9XX0iLCJqdGkiOiJaR1E0TVRBMU9USXRORE5sTkMwME1UYzBMV0V4TXpJdFkyRTFZVEZqWTJZNVpUWXoiLCJleHAiOjE2MTMxOTgwOTZ9.jln9bHWAtKTmmGvdsqCPVIzDlQ3vsMBDmrMN9NTevJRUvPZTBGT27hS4E6Gtp8wv1d3YcBmY3qlXwzvJWhuRCwug8g8Gim-tTBlEhOy-Ae-_uWxLvcN9PgAG8BXyaC_0XMI81E9hlD0S9Q4MLzBejeoQFpSr34it5PbmSqBgQuvzM5ofF6q3tyZzCClSDybLXlblo8R8LrvBEX3JEWWR-GftGaHrGPqSCedBbj0cHlLrlQraOM3TEXGOO-depNQh6Eguz6lt17JOqOxiI4FZaCSKO-tUpxzEx1DdAARhisK9z5g5WHjD_y4_LJi61SXBS-5fqNRSfQxtzN1rbkl25A" ;
  @Autowired
    private StringRedisTemplate stringRedisTemplate ;
  @Autowired
    private AnnouncementMapper announcementMapper ;
@Autowired
    private RedisTemplate<Object,Announcement> AnnouncementRedisTemplate ;
  @Test
    void contextLoads() throws Exception {
//        Map<String, Object> map = RsaUtils.GenerateKey("lms", 2048);
//        byte[] publicKey=(byte[])  map.get("publicKey");
//        byte[] privateKey=(byte[]) map.get("privateKey");
//        RedisSerializer redisSerializer = new    StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setValueSerializer(redisSerializer);
//        redisTemplate.opsForValue().set("publicKey",new String(publicKey));
//        re disTemplate.opsForValue().set("privateKey",new String(privateKey));
//        BorrowCard borrowCard =new BorrowCard();
//        borrowCard.setBorrowStatus(1);
//        borrowCard.setReader("SSDSD");
//        System.out.println(borrowCardService.insertBorrowCard(borrowCard));

//     Announcement announcement =new Announcement() ;
//     announcement.setTitle("asda");
//     announcement.setDetail("asda");
//            Announcement announcement =new Announcement() ;
//            announcement.setDetail("asdas");
//            announcement.setTitle("sdsad");
//            announcement.setaId(12L);
//            AnnouncementRedisTemplate.opsForValue().set("announcement::12",announcement);


  }}
