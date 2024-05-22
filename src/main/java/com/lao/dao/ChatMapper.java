package com.lao.dao;

import com.lao.pojo.Chat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatMapper {
    List<Chat> getChatListByBid(@Param("bid") String bid);

    int addChat(Chat chat);
}
