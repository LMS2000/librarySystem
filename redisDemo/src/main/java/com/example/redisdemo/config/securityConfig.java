package com.example.redisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)
public class securityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
    private StringRedisTemplate stringRedisTemplate ;
   @Autowired
   private MyAuthenticationProvider myAuthenticationProvider;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(this.myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers(HttpMethod.DELETE,"/book/**","/bookSort/**","/borrowBook/**","/borrowCard/**","/manager/**","/rule/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/rule/**","/bookSort/**","/book/**","/manager/**","/borrowCard/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/rule/**","/bookSort/**","/manager/**","/book/**","/borrowCard/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/rule/**","bookSort/**","/manager/**","/borrowCard/**","/borrowBook").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/borrowBook/**","/announcement/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/announcement/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/borrowBook/**","/announcement/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/borrowBook/returned","/borrowBook/notReturned").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/borrowBook/userReturned","/borrowBook/userNotReturned").hasRole("GUEST")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new WebSecurityConfig(super.authenticationManager(),stringRedisTemplate))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
