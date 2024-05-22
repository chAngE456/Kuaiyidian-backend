package com.lao.controller;

import com.lao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    UserService userService;
    @RequestMapping("/test/{bid}")
    public String LoginTest(@PathVariable String bid){
        System.out.println(bid);
        return userService.getNewAccount();
    }
}
