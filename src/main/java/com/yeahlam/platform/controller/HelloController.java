package com.yeahlam.platform.controller;

import com.yeahlam.platform.mapper.userMapper;
import com.yeahlam.platform.pojo.user;
import com.yeahlam.platform.service.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HelloController {
    @Autowired
    userServices userServices;

    @GetMapping("/")
    public String hello(Model model, HttpServletRequest request, HttpSession session) {
        user user = userServices.getUserByCookie(request);
        if (user != null) {
            session.setAttribute("user", user);
        }
        return "index";
    }
}
