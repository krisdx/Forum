package com.forum.areas.question.model.view;

import java.io.Serializable;

public class CommentViewModel implements Serializable {

    private Long id;
    private String comment;
    private String username;
    private String date;
    private int likesCount;
    private int dislikesCount;
    private Boolean isLiked;
    private boolean hasBeenEdited;

    public CommentViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public void setIsLiked(Boolean liked) {
        this.isLiked = liked;
    }

    public boolean getHasBeenEdited() {
        return this.hasBeenEdited;
    }

    public void setHasBeenEdited(boolean hasBeenEdited) {
        this.hasBeenEdited = hasBeenEdited;
    }
}