package com.lao.config;

import com.lao.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 如果是OPTIONS请求，则不执行任何操作，直接返回true
            return true;
        }
        // 从请求头、请求参数或Cookie中获取token
        String token = request.getHeader("token");// 假设token通过"token"头传输
        //System.out.println("token:" + token);
        TokenUtil tokenUtil = new TokenUtil();
        Claims claims = tokenUtil.validateToken(token);
        //System.out.println("claims:" + claims);
        // 如果token无效或不存在，返回错误响应
        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false; // 阻止请求继续处理
        }
        // 如果token有效，继续处理请求
        return true;
    }
}
