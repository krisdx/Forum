package com.forum.areas.question.controller;

import com.forum.areas.question.model.bind.CommentSaveModel;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.view.CommentViewModel;
import com.forum.areas.question.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public CommentViewModel addComment(@ModelAttribute CommentSaveModel commentSaveModel) {
        return this.commentService.save(commentSaveModel);
    }

    @ResponseBody
    @PutMapping("/edit/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public CommentViewModel editComment(@PathVariable("commentId") Long commentId, @ModelAttribute EditBindModel commentEditBindModel) {
        return this.commentService.edit(commentId, commentEditBindModel);
    }

    @ResponseBody
    @DeleteMapping("/delete/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        this.commentService.delete(commentId);
    }
}