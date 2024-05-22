package com.lao.controller;


import com.lao.pojo.User;
import com.lao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/getAccount")
    public String getNewAccount(Model model){
        String acc = userService.getNewAccount();
        model.addAttribute("account", acc);
        return acc;
    }

    @PostMapping("/reg")
    public String RegisterUser(@RequestBody User user){
        userService.CreateNewAccount(user.getAccount(),user.getPassword(),user.getName());
        return "success";
    }

    @GetMapping("/checkAccount")
    public ResponseEntity<?> checkAccount(@RequestParam("account") String account){
        if(userService.getUserByAccount(account)==null){
            return ResponseEntity.ok("ok");
        }
        else{
            return ResponseEntity.ok("error");
        }
    }
}
