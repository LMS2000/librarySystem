package com.example.redisdemo.mapper;
import com.example.redisdemo.domain.LoginInTable;


public interface LoginMapper {


   /*
    * @Param 用户名
    * @Return 用户信息
   *
   */
  LoginInTable queryAdmin(LoginInTable loginInTable );

  /*
   * @Param 用户名
   * @Return 修改结果
   *
   */

  int  updateLastLogin(String username);

  /*
   * @Param 修改的信息
   * @Return 修改结果
   *
   */
  int updateLoginInTable(LoginInTable loginInTable );
  /*
   * @Param 用户的id
   * @Return 删除结果
   *
   */
  int  deleteLoginInTableById(Long uId);

  LoginInTable  findAccountById(Long uId);

  int insertAccount(LoginInTable loginInTable );
}
