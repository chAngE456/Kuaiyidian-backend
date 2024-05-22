package com.lao.service.Impl;

import com.lao.dao.ChatMapper;
import com.lao.pojo.Chat;
import com.lao.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<Chat> getBookingChats(String bid) {
        return chatMapper.getChatListByBid(bid);
    }

    @Override
    public int addNewChat(Chat chat) {
        return chatMapper.addChat(chat);
    }
}
