package com.forum.areas.question.entity;

import com.forum.areas.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question implements Likable, Editable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "dislikes_count")
    private int dislikesCount;

    @Basic
    private String title;

    @Basic
    private String question;

    @OneToOne
    private Category category;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "has_been_edited")
    private Boolean hasBeenEdited;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private UserLikes userLikes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questions_tags",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "questions_comments",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private Set<Comment> comments;

    public Question() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLikes getUserLikes() {
        if (this.userLikes == null) {
            this.setUserLikes(new UserLikes());
        }

        return this.userLikes;
    }

    public void setUserLikes(UserLikes questionUserLikes) {
        this.userLikes = questionUserLikes;
    }

    public Set<Tag> getTags() {
        if (this.tags == null) {
            this.setTags(new HashSet<>());
        }

        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        if (this.comments == null) {
            this.setComments(new HashSet<>());
        }

        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}