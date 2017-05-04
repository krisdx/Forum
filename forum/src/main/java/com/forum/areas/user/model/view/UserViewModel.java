package com.forum.areas.user.model.view;

import java.io.Serializable;

public class UserViewModel implements Serializable {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String bio;
    private char gender;
    private String registerDate;
    private int questionsPostedCount;
    private int commentsPostedCount;
    private int totalLikesReceived;
    private int totalDislikesReceived;

    public UserViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getQuestionsPostedCount() {
        return this.questionsPostedCount;
    }

    public void setQuestionsPostedCount(int questionsPostedCount) {
        this.questionsPostedCount = questionsPostedCount;
    }

    public int getCommentsPostedCount() {
        return this.commentsPostedCount;
    }

    public void setCommentsPostedCount(int commentsPostedCount) {
        this.commentsPostedCount = commentsPostedCount;
    }

    public int getTotalLikesReceived() {
        return this.totalLikesReceived;
    }

    public void setTotalLikesReceived(int totalLikesReceived) {
        this.totalLikesReceived = totalLikesReceived;
    }

    public int getTotalDislikesReceived() {
        return this.totalDislikesReceived;
    }

    public void setTotalDislikesReceived(int totalDislikesReceived) {
        this.totalDislikesReceived = totalDislikesReceived;
    }
}