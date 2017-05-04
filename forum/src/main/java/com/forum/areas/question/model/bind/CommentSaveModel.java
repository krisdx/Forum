package com.forum.areas.question.model.bind;

import java.io.Serializable;

public class CommentSaveModel implements Serializable {

    private Long questionId;
    private String comment;

    public CommentSaveModel() {
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}