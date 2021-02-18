package com.example.redisdemo.utils;

import com.example.redisdemo.domain.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JWTokenUtil  {
   private static final String JWT_PAYLOAD_USER_KEY="user";


   /*
   * 私钥加密
   *
   * */
    public static String generateTokenExpire(Object userInfo, PrivateKey privateKey ,Long expire)
    {
        Date date=new Date();
         return Jwts.builder()
                    .claim(JWT_PAYLOAD_USER_KEY,JsonUtils.toString(userInfo))
                    .setId(CreateJTI())
                    .setExpiration(new Date(date.getTime()+expire))
                    .signWith(SignatureAlgorithm.RS256,privateKey)
                    .compact();
    }

    /*
    * 生成随机id
    * */
    public  static String CreateJTI()
    {
        return  new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /*
    * 将json jwt转换为对象
    * */
    public  static Jws<Claims> parseJWT(String jwt, PublicKey publicKey )
    {
     return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt);
    }

    /*
    *获取token中的载荷信息
    * */
     public static <T>Payload<T> getUserInfoFromToken(String token,PublicKey publicKey ,Class<T> userType)
     {
         Jws<Claims> claimsJws = parseJWT(token, publicKey); //获取转换后得到的token对象
         Claims body =claimsJws.getBody(); //获取jws中的body部分
         Payload<T> claims = new Payload<>();//创建 临时存储claims(声明)的对象
         //装入荷载的一些信息（id,过期时间，用户的信息）
         claims.setExpiration(body.getExpiration());
         claims.setId(body.getId());
         claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(),userType));
         return claims;
     }


    public static <T>Payload<T> getUserInfoFromToken(String token,PublicKey publicKey)
    {
        Jws<Claims> claimsJws =parseJWT(token,publicKey);
        Claims body =claimsJws.getBody();
        Payload<T> claims= new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }


}
