package com.lao.controller;

import com.lao.pojo.Chat;
import com.lao.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/list")
    public ResponseEntity<?> getChatListData(@RequestParam("bid")String bid){
        List<Chat> list = chatService.getBookingChats(bid);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addChat(@RequestBody Chat chat){
        chat.setCreate_time(new Date(System.currentTimeMillis()));
        chatService.addNewChat(chat);
        return ResponseEntity.ok("发送成功");
    }
}
