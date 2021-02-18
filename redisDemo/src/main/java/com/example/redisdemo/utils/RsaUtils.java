package com.example.redisdemo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RsaUtils {

    private  static  final int DEFAULT_KEY_SIZE=2048;
      //将公钥的字节流转换为公钥对象
    public static PublicKey getPublicKey(byte[] bytes) throws Exception
    {
          bytes= Base64.getDecoder().decode(bytes );
        X509EncodedKeySpec spec=new X509EncodedKeySpec(bytes) ;
        KeyFactory keyFactory =KeyFactory.getInstance("RSA");
        return  keyFactory.generatePublic(spec);
    }
    //将公钥的字节流转换为公钥对象
    public static PrivateKey getPrivateKey(byte[] bytes) throws Exception
    {
        bytes= Base64.getDecoder().decode(bytes );
        PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec (bytes) ;
        KeyFactory keyFactory =KeyFactory.getInstance("RSA");
        return  keyFactory.generatePrivate(spec );
    }
        //定制rsa 并且输出到指定目录下的指定文件
    public static Map<String,Object> GenerateKey(String secret,int keySize) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom =new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        Map<String,Object> map = new HashMap<>();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
       map.put("publicKey",publicKeyBytes);
       map.put("privateKey",privateKeyBytes);
       return map;
    }
}
