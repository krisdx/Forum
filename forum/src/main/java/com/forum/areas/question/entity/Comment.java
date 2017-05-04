package com.forum.areas.question.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("SameParameterValue")
@Entity
@Table(name = "comments")
public class Comment implements Likable, Editable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String comment;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "dislikes_count")
    private int dislikesCount;

    @OneToOne(cascade = CascadeType.ALL)
    private UserLikes userLikes;

    @Basic
    private String username;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "has_been_edited")
    private Boolean hasBeenEdited;

    public Comment() {
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

    @Override
    public int getLikesCount() {
        return this.likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public int getDislikesCount() {
        return this.dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public UserLikes getUserLikes() {
        if (this.userLikes == null) {
            this.setUserLikes(new UserLikes());
        }

        return this.userLikes;
    }

    public void setUserLikes(UserLikes userLikes) {
        this.userLikes = userLikes;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Boolean getHasBeenEdited() {
        return this.hasBeenEdited;
    }

    public void setHasBeenEdited(Boolean hasBeenEdited) {
        this.hasBeenEdited = hasBeenEdited;
    }
}