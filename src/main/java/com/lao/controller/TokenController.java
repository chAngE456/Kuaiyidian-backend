package com.lao.controller;

import com.lao.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    @GetMapping("/check")
    public String CheckToken(HttpServletRequest request) {
        TokenUtil tokenUtil = new TokenUtil();
        String token = request.getHeader("token");
        //System.out.println("token:" + token);
        Claims claims = tokenUtil.validateToken(token);
        if(claims != null) {
            return "OK";
        }
        return "NEED LOGIN";
    }

}
