package com.forum.areas.question.service;

import com.forum.areas.question.entity.Comment;
import com.forum.areas.question.entity.UserLikes;
import com.forum.areas.question.repository.CommentRepository;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.repository.UserRepository;
import com.forum.util.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("Duplicates")
public class CommentLikableServiceImpl implements CommentLikableService, LikableService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentLikableServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean like(Long commentId) {
        Comment comment = this.commentRepository.findOne(commentId);
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());

        UserLikes userLikes = comment.getUserLikes();
        Boolean result = userLikes.like(currentUser.getId());
        if (result == null) {
            comment.setLikesCount(comment.getLikesCount() - 1);
        } else if (result) {
            comment.setLikesCount(comment.getLikesCount() + 1);
        } else /* if (!result) */ {
            comment.setLikesCount(comment.getLikesCount() + 1);
            comment.setDislikesCount(comment.getDislikesCount() - 1);
        }

        this.commentRepository.save(comment);
        return result;
    }

    @Override
    public Boolean dislike(Long questionId) {
        Comment comment= this.commentRepository.findOne(questionId);
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());

        UserLikes userLikes = comment.getUserLikes();
        Boolean result = userLikes.dislike(currentUser.getId());
        if (result == null) {
            comment.setDislikesCount(comment.getDislikesCount() - 1);
        } else if (result) {
            comment.setDislikesCount(comment.getDislikesCount() + 1);
        } else /* if (!result) */ {
            comment.setDislikesCount(comment.getDislikesCount() + 1);
            comment.setLikesCount(comment.getLikesCount() - 1);
        }

        this.commentRepository.save(comment);
        return result;
    }
}