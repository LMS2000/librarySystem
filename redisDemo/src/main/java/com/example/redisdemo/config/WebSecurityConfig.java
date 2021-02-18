package com.example.redisdemo.config;

import com.example.redisdemo.domain.Payload;
import com.example.redisdemo.domain.UserPojo;
import com.example.redisdemo.utils.JWTokenUtil;
import com.example.redisdemo.utils.RsaUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class WebSecurityConfig extends BasicAuthenticationFilter  {
     private StringRedisTemplate stringRedisTemplate ;

     public WebSecurityConfig(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
     }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header==null|| !header.startsWith("Bearer"))
        {
        chain.doFilter(request,response);
        return;
        }else
        {
            String token  =header.replace("Bearer","");
            Payload<UserPojo> userPojoPayload  = null;
            try {
                userPojoPayload = JWTokenUtil.getUserInfoFromToken(token , RsaUtils.getPublicKey(stringRedisTemplate.opsForValue().get("publicKey").getBytes())  , UserPojo.class);
            }catch (ExpiredJwtException e){
               response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter writer = response.getWriter();
                Map<String,Object> map = new HashMap<>();
               map.put("msg","token已过期,请重新登陆");
                map.put("code",1004);
                writer.write(new ObjectMapper().writeValueAsString(map));
                writer.flush();
                writer.close();
                return;
            }
            catch (Exception sin)
            {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter writer = response.getWriter();
                Map<String,Object> map = new HashMap<>();
                map.put("msg","token签字解析失败！");
                map.put("code",HttpServletResponse.SC_FORBIDDEN);
                writer.write(new ObjectMapper().writeValueAsString(map));
                writer.flush();
                writer.close();
                return;

            }

            UserPojo user=  userPojoPayload.getUserInfo();
            if(user!=null)
            {
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request ,response );
            }
        }
    }
}
