package com.forum.areas.question.controller;

import com.forum.areas.question.exception.NoSuchCategoryException;
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
@RequestMapping("categories")
public class CategoryController {

    private final QuestionService questionService;

    @Autowired
    public CategoryController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{category}")
    public String questionsByCategory(@PathVariable("category") String category, Model model) {
        List<QuestionViewMiniModel> questionViewMiniModels =
                this.questionService.findQuestionsByCategory(category);

        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_BASE_NAME);
        if (questionViewMiniModels.size() == 0) {
            String message = String.format(resourceBundle.getString("title.emptyQuestionsByCategoryTemplate"), category);
            model.addAttribute("message", message);
            return "questions/empty-template";
        }

        String title = String.format(resourceBundle.getString("title.questionsByCategory"), category);
        model.addAttribute("title", title);
        model.addAttribute("questionViewMiniModels", questionViewMiniModels);
        return "questions/question-mini-models";
    }

    @ExceptionHandler(NoSuchCategoryException.class)
    public String categoryNotFound(Model model) {
        model.addAttribute("title", "error.noSuchCategory");
        return "error/error-template";
    }
}