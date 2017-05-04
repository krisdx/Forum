package com.forum.areas.user.service;

import com.forum.areas.question.entity.Question;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.exception.NoSuchUserException;
import com.forum.areas.user.model.view.UserViewModel;
import com.forum.areas.user.repository.UserRepository;
import com.forum.util.AuthenticationUtils;
import com.forum.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User addQuestionToCurrentUser(Question question) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = this.userRepository.findByUsername(username);
        currentUser.addQuestion(question);
        return this.userRepository.save(currentUser);
    }

    @Override
    public UserViewModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new NoSuchUserException();
        }

        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        userViewModel.setRegisterDate(this.parseDate(user.getRegisterDate()));
        userViewModel.setQuestionsPostedCount(user.getQuestions().size());
        userViewModel.setCommentsPostedCount(this.userRepository.commentsCountByUsername(user.getUsername()));
        userViewModel.setTotalLikesReceived(this.getTotalLikesCount(user));
        userViewModel.setTotalDislikesReceived(this.setTotalDislikesCount(user));

        return userViewModel;
    }

    @Override
    public void changeLanguage(String lang) {
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());
        currentUser.setLanguage(lang);
        this.userRepository.save(currentUser);
    }

    @Override
    public String getCurrentLanguage() {
        User currentUser = this.userRepository.findByUsername(AuthenticationUtils.getCurrentUserUsername());
        if (currentUser == null) {
            return null;
        }

        return currentUser.getLanguage();
    }

    @Override
    public void editBio(Long userId, EditBindModel bioEditBindModel) {
        User user = this.userRepository.findOne(userId);
        if (user.getBio() != null && user.getBio().equals(bioEditBindModel.getEditedText())) {
            return;
        }

        if (bioEditBindModel.getEditedText().equals("")) {
            bioEditBindModel.setEditedText(null);
        }

        user.setBio(bioEditBindModel.getEditedText());
        this.userRepository.save(user);
    }

    private int getTotalLikesCount(User user) {
        int totalLikes = 0;
        for (Question question : user.getQuestions()) {
            totalLikes += question.getUserLikes().getTotalLikesCount();
        }

        return totalLikes;
    }

    private int setTotalDislikesCount(User user) {
        int totalDislikes = 0;
        for (Question question : user.getQuestions()) {
            totalDislikes += question.getUserLikes().getTotalDislikesCount();
        }

        return totalDislikes;
    }

    private String parseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_FORMAT);
        return date.format(formatter);
    }
}