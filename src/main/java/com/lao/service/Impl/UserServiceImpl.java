package com.lao.service.Impl;

import com.lao.dao.UserMapper;
import com.lao.pojo.User;
import com.lao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        User user = userMapper.getUserByAccount(account);
        return user;
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean CheckPwd(String checkAccount,String inputPwd) {
        User user =  getUserByAccount(checkAccount);
        return Objects.equals(user.getPassword(), inputPwd);
    }

    @Override
    public String getNewAccount() {
        Random random = new Random();
        String account = "";
        while (true){
            account = "";
            for (int i = 0; i < 10; i++) {
                account = account + (char)(random.nextInt(10)+48);
            }
            if(userMapper.getUserByAccount(account)==null){
                break;
            }
        }
        return account;
    }

    @Override
    public String getRandomUid() {
        Random random = new Random();
        String uid = "";
        while (true){
            uid = "";
            for (int i = 0; i < 10; i++) {
                int j = random.nextInt(3);
                switch (j){
                    case 0:
                        uid = uid + (char)(random.nextInt(10)+48);
                        break;
                    case 1:
                        uid = uid + (char)(random.nextInt(26)+97);
                        break;
                    case 2:
                        uid = uid + (char)(random.nextInt(26)+65);
                        break;
                }
            }
            if(userMapper.getUserById(uid)==null){
                break;
            }
        }
        return uid;
    }

    @Override
    public void CreateNewAccount(String account, String password, String name) {
        String uid = getRandomUid();
        Date date = new Date(System.currentTimeMillis());
        User user = new User(uid,name,password,0,date,account);
        userMapper.addUser(user);
    }
}
