package com.forum.areas.question.controller;

import com.forum.areas.question.service.CommentLikableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentLikableController {

    private final CommentLikableService commentLikableService;

    @Autowired
    public CommentLikableController(CommentLikableService commentLikableService) {
        this.commentLikableService = commentLikableService;
    }

    @ResponseBody
    @PostMapping("/comments/like/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public Boolean likeComment(@PathVariable("commentId") Long commentId) {
        return this.commentLikableService.like(commentId);
    }

    @ResponseBody
    @PostMapping("/comments/dislike/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public Boolean dislikeComment(@PathVariable("commentId") Long commentId) {
        return this.commentLikableService.dislike(commentId);
    }
}