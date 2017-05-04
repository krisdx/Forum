package com.forum.areas.question.service;

import com.forum.areas.question.entity.Question;
import com.forum.areas.question.entity.UserLikes;
import com.forum.areas.question.repository.QuestionRepository;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.repository.UserRepository;
import com.forum.util.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("Duplicates")
public class QuestionLikableServiceImpl implements QuestionLikableService, LikableService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestionLikableServiceImpl(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean like(Long questionId) {
        Question question = this.questionRepository.findOne(questionId);
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());

        UserLikes userLikes = question.getUserLikes();
        Boolean result = userLikes.like(currentUser.getId());
        if (result == null) {
            question.setLikesCount(question.getLikesCount() - 1);
        } else if (result) {
            question.setLikesCount(question.getLikesCount() + 1);
        } else /* if (!result) */ {
            question.setLikesCount(question.getLikesCount() + 1);
            question.setDislikesCount(question.getDislikesCount() - 1);
        }

        this.questionRepository.save(question);
        return result;
    }

    @Override
    public Boolean dislike(Long questionId) {
        Question question = this.questionRepository.findOne(questionId);
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());

        UserLikes userLikes = question.getUserLikes();
        Boolean result = userLikes.dislike(currentUser.getId());
        if (result == null) {
            question.setDislikesCount(question.getDislikesCount() - 1);
        } else if (result) {
            question.setDislikesCount(question.getDislikesCount() + 1);
        } else /* if (!result) */ {
            question.setDislikesCount(question.getDislikesCount() + 1);
            question.setLikesCount(question.getLikesCount() - 1);
        }

        this.questionRepository.save(question);
        return result;
    }
}