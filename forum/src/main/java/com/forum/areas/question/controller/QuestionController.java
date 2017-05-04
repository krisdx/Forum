package com.forum.areas.question.controller;

import com.forum.areas.question.exception.NoSuchQuestionException;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.bind.QuestionAddBindModel;
import com.forum.areas.question.model.view.CategoryViewModel;
import com.forum.areas.question.model.view.QuestionViewMiniModel;
import com.forum.areas.question.model.view.QuestionViewModel;
import com.forum.areas.question.service.CategoryService;
import com.forum.areas.question.service.QuestionService;
import com.forum.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;
    private final CategoryService categoryService;

    @Autowired
    public QuestionController(QuestionService questionService, CategoryService categoryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String showAllQuestions(Model model) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_BASE_NAME);
        List<QuestionViewMiniModel> questionViewMiniModels = this.questionService.findAllWithoutCommentsAndTags();
        if (questionViewMiniModels == null || questionViewMiniModels.size() == 0) {
            String message = resourceBundle.getString("title.noQuestions");
            model.addAttribute("message", message);
            return "questions/empty-template";
        }

        String title = resourceBundle.getString("title.allQuestions");
        model.addAttribute("questionViewMiniModels", questionViewMiniModels);
        model.addAttribute("title", title);
        return "questions/question-mini-models";
    }

    @GetMapping("/add")
    public String showAddQuestionPage(@ModelAttribute QuestionAddBindModel questionAddBindModel, Model model) {
        List<CategoryViewModel> categoryViewModels = this.categoryService.findAllViewModels();
        model.addAttribute("categoryViewModels", categoryViewModels);
        return "questions/add-question";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public String addQuestion(@Valid @ModelAttribute QuestionAddBindModel questionAddBindModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("questionBindModel", questionAddBindModel);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindResult", bindingResult);
            return "redirect:/questions/add";
        }

        this.questionService.add(questionAddBindModel);
        return "redirect:/questions/all";
    }

    @GetMapping("/{questionId}")
    public String showQuestionById(@PathVariable("questionId") Long questionId, Model model) {
        QuestionViewModel questionViewModel = this.questionService.findById(questionId);
        model.addAttribute("questionViewModel", questionViewModel);
        return "questions/question";
    }

    @ResponseBody
    @PutMapping("/edit/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public QuestionViewModel editQuestion(@PathVariable("questionId") Long questionId, @ModelAttribute EditBindModel questionEditBindModel) {
        return this.questionService.edit(questionId, questionEditBindModel);
    }

    @ResponseBody
    @DeleteMapping("/delete/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.delete(questionId);
    }

    @ExceptionHandler(NoSuchQuestionException.class)
    public String questionNotFound(Model model) {
        model.addAttribute("title", "error.noSuchQuestion");
        return "error/error-template";
    }
}