package com.forum.areas.question.service;

import com.forum.areas.question.entity.Comment;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.bind.QuestionAddBindModel;
import com.forum.areas.question.model.view.QuestionViewMiniModel;
import com.forum.areas.question.model.view.QuestionViewModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface QuestionService {

    @PreAuthorize("hasRole('USER')")
    void add(QuestionAddBindModel questionAddBindModel);

    List<QuestionViewMiniModel> findAllWithoutCommentsAndTags();

    QuestionViewModel findById(Long id);

    @PreAuthorize("hasRole('USER')")
    void addComment(Long questionId, Comment comment);

    @PreAuthorize("hasRole('USER')")
    QuestionViewModel edit(Long questionId, EditBindModel questionEditBindModel);

    @PreAuthorize("hasRole('USER')")
    void delete(Long questionId);

    List<QuestionViewMiniModel> findQuestionsByTag(String tag);

    List<QuestionViewMiniModel> findQuestionsByCategory(String category);
}