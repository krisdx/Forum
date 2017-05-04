package com.forum.areas.question.service;

import com.forum.areas.question.model.bind.CommentSaveModel;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.view.CommentViewModel;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('USER')")
public interface CommentService {
    CommentViewModel save(CommentSaveModel commentSaveModel);

    CommentViewModel edit(Long commentId, EditBindModel editBindModel);

    void delete(Long commentId);
}