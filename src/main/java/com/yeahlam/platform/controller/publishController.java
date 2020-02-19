package com.yeahlam.platform.controller;

import com.yeahlam.platform.DTO.accessTokenDTO;
import com.yeahlam.platform.DTO.githubUserDTO;
import com.yeahlam.platform.Provider.githubProvider;
import com.yeahlam.platform.mapper.QuestionMapper;
import com.yeahlam.platform.mapper.userMapper;
import com.yeahlam.platform.pojo.Question;
import com.yeahlam.platform.pojo.user;
import com.yeahlam.platform.service.QuestionServices;
import com.yeahlam.platform.service.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class publishController {

    @Autowired
    githubProvider githubProvider;

    @Autowired
    userMapper userMapper;

    @Autowired
    QuestionServices QuestionServices;

    @Autowired
    userServices userServices;

    @GetMapping("/publish")
    public String githubCallback() {
        return "publish";
    }

    @PostMapping("/publish")
    public String createQuestion(String title, String description, String tag, Model model, HttpServletRequest request) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        String checkQuestionVaild = QuestionServices.checkQuestionVaild(question);
        user loginUser = userServices.getUserByCookie(request);
        if (loginUser == null) {
            model.addAttribute("errorMessage", "用户必须登录");
            return "publish";

        }
        if (checkQuestionVaild != null) {
            model.addAttribute("errorMessage", checkQuestionVaild);
            return "publish";

        }
        question.setCreator(loginUser.getId());
        System.out.println(question);
        QuestionServices.createQuestion(question);
        System.out.println(question);
        return "redirect:/";
    }
}
