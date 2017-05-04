package com.forum.areas.question.model.view;

import java.io.Serializable;

/**
 * Mini version of the question view model, for performance purposes and convenience.
 */
public class QuestionViewMiniModel implements Serializable {

    private Long id;
    private String title;
    private String username;
    private String date;
    private String category;
    private int likesCount;
    private int dislikesCount;
    /* This boolean field must be it's wrapper class, because
     Thymeleaf uses the null value. */
    private Boolean isLiked;

    public QuestionViewMiniModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return this.dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public Boolean getIsLiked() {
        return this.isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }
}