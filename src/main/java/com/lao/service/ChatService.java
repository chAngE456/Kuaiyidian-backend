package com.lao.service;

import com.lao.pojo.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getBookingChats(String bid);

    int addNewChat(Chat chat);


}
