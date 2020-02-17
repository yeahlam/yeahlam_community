package com.yeahlam.platform.controller;

import com.yeahlam.platform.DTO.accessTokenDTO;
import com.yeahlam.platform.DTO.githubUserDTO;
import com.yeahlam.platform.Provider.githubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class githubController {
    @Value("${github.config.client_id}")
    private String Client_id;
    @Value("${github.config.client_secret}")
    private String Client_secret;
    @Autowired
    githubProvider githubProvider;
    @GetMapping("/callback")

    public String githubCallback(@RequestParam String code,@RequestParam String state){
        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        githubUserDTO user = githubProvider.getUserByToken(accessToken);
        System.out.println(user);
        return "index";
    }
}
