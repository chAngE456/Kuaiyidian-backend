package com.lao.service;

import com.lao.pojo.User;

public interface UserService {
    //通过账号获取密码
    User getUserByAccount(String account);

    User getUserById(String id);

    //检查密码是否正确
    boolean CheckPwd(String checkAccount,String inputPwd);

    //获取一个新的账号
    String getNewAccount();

    //获取一个随机的uid
    String getRandomUid();

    //创建新账户
    void CreateNewAccount(String account , String password, String name);


}
