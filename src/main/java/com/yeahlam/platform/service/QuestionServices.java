package com.yeahlam.platform.service;

import com.yeahlam.platform.mapper.QuestionMapper;
import com.yeahlam.platform.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServices {
    @Autowired
    QuestionMapper questionMapper;
    public void createQuestion(Question question){
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(System.currentTimeMillis());
        questionMapper.InsertQuestion(question);
    }
    public String checkQuestionVaild(Question question){
        if("".equals(question.getTitle())){
            return "题目不能为空";
        }
        if("".equals(question.getDescription())){
            return "描述不能为空";
        }
        if("".equals(question.getTag())){
            return "标签不能为空";
        }
        return null;
    }
}
