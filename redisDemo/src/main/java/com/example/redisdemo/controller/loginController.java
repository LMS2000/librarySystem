package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.UserPojo;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.LoginService;
import com.example.redisdemo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@ResponseResult
public class loginController {
    @Autowired
    private LoginService loginService ;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/login")
    @ResponseResult
    public Map<String,Object> securityLogin(@RequestParam("account") String account, @RequestParam("password")String password, HttpServletResponse response) throws IOException {
        /**
         * 使用security的认证管理器进行认证
         */
        Authentication authenticate = null;
//        response.setContentType("application/json;charset=utf-8");
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account,password));

        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        //生成token
        final UserPojo user = (UserPojo) authenticate.getPrincipal();
        String token = null;
        if(user != null){
            // token信息保存在request域，随后保存在响应头
            try {
                token = JWTokenUtil.generateTokenExpire(user, RsaUtils.getPrivateKey(stringRedisTemplate.opsForValue().get("privateKey").getBytes()) ,24L*60L*1000L*60L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Authorization","Bearer"+token);
        Map<String,Object> resultMap= new HashMap<>() ;
        resultMap.put("username",user.getUsername());
        resultMap.put("uid",user.getId());
        return resultMap;
    }
    @PostMapping("/{oldPassword}/{newPassword}")
    @ResponseResult
    public void updatePassword(@PathVariable String oldPassword, @PathVariable String newPassword, HttpServletRequest Request ) throws Exception {
        String token =Request.getHeader("Authorization");
        String jwt=token.replace("Bearer","");
        int i = loginService.updatePassword(jwt, oldPassword, newPassword);
        if(i<=0){
            throw new MethodFailureRunException("数据修改失败");
        }
    }

}
