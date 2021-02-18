package com.example.redisdemo.service.Impl;

import com.example.redisdemo.domain.*;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.mapper.LoginMapper;
import com.example.redisdemo.service.LoginService;
import com.example.redisdemo.utils.JWTokenUtil;
import com.example.redisdemo.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;

    @Override
    public int updatePassword(String token,String oldPassword,String newPassword) throws Exception {


        Long id = getUid(token );
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        LoginInTable accountById = loginMapper.findAccountById(id);
        if(!bCryptPasswordEncoder.matches(oldPassword,accountById.getPassword()))
        {
            throw new DataNoFoundException("旧密码不正确！");
        }
        LoginInTable loginInTable =new LoginInTable() ;
        loginInTable.setuId(id);
        loginInTable.setPassword(newPassword);
        if(!checkUsernameIsExist(id)){ throw new DataNoFoundException("操作数据不存在！");}
        int   result=   loginMapper.updateLoginInTable(loginInTable );
        return result;
    }
    public Long  getUid(String token) throws Exception {
        Payload<UserPojo> userPojoPayload =
                JWTokenUtil
                        .getUserInfoFromToken(token , RsaUtils.getPublicKey(stringRedisTemplate.opsForValue().get("publicKey").getBytes())  , UserPojo.class);
        UserPojo userInfo = userPojoPayload.getUserInfo();
        Long id = userInfo.getId();
        return id;
    }
    public Boolean checkUsernameIsExist(Long uId){

        LoginInTable loginInTable = loginMapper.findAccountById(uId );
        return loginInTable==null?false:true;
    }
}
