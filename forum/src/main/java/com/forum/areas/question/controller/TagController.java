package com.forum.areas.question.controller;

import com.forum.areas.question.exception.NoSuchTagException;
import com.forum.areas.question.model.view.QuestionViewMiniModel;
import com.forum.areas.question.service.QuestionService;
import com.forum.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ResourceBundle;

@Controller
@RequestMapping("tags")
public class TagController {

    private final QuestionService questionService;

    @Autowired
    public TagController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{tag}")
    public String questionsByTag(@PathVariable("tag") String tag, Model model) {
        List<QuestionViewMiniModel> questionViewMiniModels =
                this.questionService.findQuestionsByTag(tag);

        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_BASE_NAME);
        if (questionViewMiniModels.size() == 0) {
            String emptyTemplateMessage = String.format(resourceBundle.getString("title.emptyQuestionsByTagTemplate"), tag);
            model.addAttribute("message", emptyTemplateMessage);
            return "questions/empty-template";
        }

        String title = String.format(resourceBundle.getString("title.questionsByTag"), tag);
        model.addAttribute("title", title);
        model.addAttribute("questionViewMiniModels", questionViewMiniModels);
        return "questions/question-mini-models";
    }

    @ExceptionHandler(NoSuchTagException.class)
    public String tagNotFound(Model model) {
        model.addAttribute("title", "error.noSuchTag");
        return "error/error-template";
    }
}