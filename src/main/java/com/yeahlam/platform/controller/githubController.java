package com.yeahlam.platform.controller;

import com.yeahlam.platform.DTO.accessTokenDTO;
import com.yeahlam.platform.DTO.githubUserDTO;
import com.yeahlam.platform.Provider.githubProvider;
import com.yeahlam.platform.mapper.userMapper;
import com.yeahlam.platform.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class githubController {
    @Value("${github.config.client_id}")
    private String Client_id;

    @Value("${github.config.client_secret}")
    private String Client_secret;

    @Autowired
    githubProvider githubProvider;

    @Autowired
    userMapper userMapper;

    @GetMapping("/callback")
    public String githubCallback(@RequestParam String code, @RequestParam String state, HttpSession session, HttpServletResponse response) {
        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        githubUserDTO userDTO = githubProvider.getUserByToken(accessToken);

        System.out.println(userDTO);
        if (userDTO != null) {
            user localuser = new user();

            localuser.setName(userDTO.getName());
            localuser.setToken(UUID.randomUUID().toString());
            localuser.setAccount_id(String.valueOf(userDTO.getId()));
            localuser.setGmt_create(System.currentTimeMillis());
            localuser.setGm_modified(localuser.getGmt_create());

            userMapper.insert(localuser);
            response.addCookie(new Cookie("token",localuser.getToken()));

            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
