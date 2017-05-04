package com.forum.areas.question.service;


import com.forum.areas.question.entity.Comment;
import com.forum.areas.question.model.bind.CommentSaveModel;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.view.CommentViewModel;
import com.forum.areas.question.repository.CommentRepository;
import com.forum.util.AuthenticationUtils;
import com.forum.util.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, QuestionService questionService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentViewModel save(CommentSaveModel commentSaveModel) {
        Comment comment = new Comment();
        comment.setDateTime(LocalDateTime.now());
        comment.setComment(commentSaveModel.getComment());
        comment.setUsername(AuthenticationUtils.getCurrentUserUsername());

        comment = this.commentRepository.save(comment);
        this.questionService.addComment(commentSaveModel.getQuestionId(), comment);
        return this.convert(comment);
    }

    @Override
    public CommentViewModel edit(Long commentId, EditBindModel editBindModel) {
        Comment comment = this.commentRepository.findOne(commentId);
        if (comment.getComment().equals(editBindModel.getEditedText())) {
            return this.convert(comment);
        }

        comment.setComment(editBindModel.getEditedText());
        comment.setDateTime(LocalDateTime.now());
        comment.setHasBeenEdited(true);
        this.commentRepository.save(comment);
        return this.convert(comment);
    }

    @Override
    public void delete(Long commentId) {
        this.commentRepository.deleteRelationsWithQuestion(commentId);
        this.commentRepository.delete(commentId);
    }

    private CommentViewModel convert(Comment comment) {
        CommentViewModel commentViewModel = this.modelMapper.map(comment, CommentViewModel.class);

        LocalDateTime dateTime = comment.getDateTime();
        String dateStr = DateUtils.parseDateTime(dateTime);
        commentViewModel.setDate(dateStr);

        return commentViewModel;
    }
}