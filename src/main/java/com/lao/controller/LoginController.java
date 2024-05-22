package com.lao.controller;

import com.lao.pojo.User;
import com.lao.service.UserService;
import com.lao.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody User user) {
        if(userService.getUserByAccount(user.getAccount()) == null){
            Map<String, String> map = new HashMap<>();
            map.put("message", "账号或密码错误");
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
        if(userService.CheckPwd(user.getAccount(),user.getPassword())) {
            User u = userService.getUserByAccount(user.getAccount());
            TokenUtil tokenUtil = new TokenUtil();
            String token = tokenUtil.createToken(u.getUid());
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            Map<String, String> map = new HashMap<>();
            map.put("message", "账号或密码错误");
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }


    }
}
