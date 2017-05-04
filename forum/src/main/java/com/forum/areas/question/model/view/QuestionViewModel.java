package com.forum.areas.question.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends QuestionViewMiniModel implements Serializable {

    private String question;
    private boolean hasBeenEdited;
    private List<TagViewModel> tags;
    private List<CommentViewModel> comments;

    public QuestionViewModel() {
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isHasBeenEdited() {
        return this.hasBeenEdited;
    }

    public void setHasBeenEdited(boolean hasBeenEdited) {
        this.hasBeenEdited = hasBeenEdited;
    }

    public List<TagViewModel> getTags() {
        return this.tags;
    }

    public void setTags(List<TagViewModel> tags) {
        this.tags = tags;
    }

    public List<CommentViewModel> getComments() {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }

        return this.comments;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }
}