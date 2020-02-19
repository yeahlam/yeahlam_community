package com.yeahlam.platform.service;

import com.yeahlam.platform.mapper.userMapper;
import com.yeahlam.platform.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class userServices {
    @Autowired
    userMapper userMapper;
    public user  getUserByCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                user user = userMapper.getUserByToken(token);
                return user;
            }
        }
        return null;
    }
}
