package com.example.redisdemo.config;
import com.example.redisdemo.domain.LoginInTable;
import com.example.redisdemo.domain.LoginMiss;
import com.example.redisdemo.domain.RolePojo;
import com.example.redisdemo.domain.UserPojo;
import com.example.redisdemo.exception.LoginCloseException;
import com.example.redisdemo.mapper.LoginMapper;
import com.example.redisdemo.mapper.MissLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MyAuthenticationProvider  implements AuthenticationProvider {
    @Autowired
    private LoginMapper loginMapper ;
    @Autowired
    private MissLoginMapper missLoginMapper;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String presentedPassword = (String)authentication.getCredentials();
        UserPojo userDeatils = null;
        // 根据用户名获取用户信息
        LoginInTable loginInTable =new LoginInTable() ;
        loginInTable.setUsername(username);
        LoginInTable user =loginMapper.queryAdmin(loginInTable);
        Long uId=user.getuId();
        if (user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            LoginMiss loginMiss = missLoginMapper.selectMissLogin(uId);
            if(loginMiss!=null)
            {
                if(loginMiss.getMissFlag()==0){
                    checkCloseTime(loginMiss.getMissTime());
                    LoginMiss loginMiss1 =new LoginMiss() ;
                    loginMiss1.setId(loginMiss.getId());
                    loginMiss1.setMissFlag(1);
                    missLoginMapper.updateMissLogin(loginMiss1);
                }
            }
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;
                if (!bCryptPasswordEncoder.matches(presentedPassword, user.getPassword())) {
                    Boolean aBoolean = checkLoginMissIsExist(uId);
                    if(aBoolean){
                           if(loginMiss.getMissNumber()==5){
                              Date date =loginMiss.getUpdateTime();
                              Long time = date.getTime()+800000L;
                              date.setTime(time);
                            LoginMiss loginMiss1 =new LoginMiss();
                            loginMiss1.setMissFlag(0);
                            loginMiss1.setMissNumber(0);
                            loginMiss1.setMissTime(date);
                            loginMiss1.setId(loginMiss.getId());
                            missLoginMapper.updateMissLogin(loginMiss1);
                        }else {
                            missLoginMapper.updateMissTime(uId);
                        }
                    }else
                    {
                        LoginMiss loginMiss1 =new LoginMiss() ;
                        loginMiss1.setuId(user.getuId());
                        loginMiss1.setMissNumber(1);
                        missLoginMapper.insertMissLogin(loginMiss1);
                    }
                    throw new BadCredentialsException("账号或密码错误！");

                }
            List<RolePojo> roles = new ArrayList<>();
            RolePojo role= new RolePojo();
            role.setRoleName(user.getRole());
            roles.add(role);
            userDeatils = new UserPojo();
            userDeatils.setRoles(roles );
            userDeatils.setId(user.getuId());
            userDeatils.setUsername(username );
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDeatils, authentication.getCredentials(), userDeatils.getAuthorities());
            result.setDetails(authentication.getDetails());
            loginMapper.updateLastLogin(user.getUsername());
            return result;
        }
    }
    public void checkCloseTime(Date missTime) throws LoginCloseException {
        Date now =new Date();
        if(missTime.getTime()>now.getTime()) {
            throw new LoginCloseException("当前账号已锁定");
        }
    }
    public Boolean checkLoginMissIsExist(Long uId)
    {
        LoginMiss loginMiss = missLoginMapper.selectMissLogin(uId);
        return loginMiss==null?false:true;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
