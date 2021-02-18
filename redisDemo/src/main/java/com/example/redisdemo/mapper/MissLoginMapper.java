package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.LoginMiss;

public interface MissLoginMapper {
    int updateMissLogin(LoginMiss loginMiss );
    int insertMissLogin(LoginMiss loginMiss);
    LoginMiss selectMissLogin(Long uId);
    int updateMissTime(Long uId);
}
