package com.lao.dao;


import com.lao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUserByAccount(String account);
    User getUserById(String id);
    int addUser(User user);
}
