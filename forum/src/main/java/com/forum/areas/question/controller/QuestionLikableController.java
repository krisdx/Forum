package com.forum.areas.question.controller;

import com.forum.areas.question.service.QuestionLikableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionLikableController {

    private final QuestionLikableService questionLikableService;

    @Autowired
    public QuestionLikableController(QuestionLikableService questionLikableService) {
        this.questionLikableService = questionLikableService;
    }

    @ResponseBody
    @PutMapping("/questions/like/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public Boolean likeQuestion(@PathVariable("questionId") Long questionId) {
        return this.questionLikableService.like(questionId);
    }

    @ResponseBody
    @PutMapping("/questions/dislike/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public Boolean dislikeQuestion(@PathVariable("questionId") Long questionId) {
        return this.questionLikableService.dislike(questionId);
    }
}