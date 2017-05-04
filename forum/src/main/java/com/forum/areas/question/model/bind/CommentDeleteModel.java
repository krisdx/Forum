package com.forum.areas.question.model.bind;

import java.io.Serializable;

public class CommentDeleteModel implements Serializable {

    private Long id;

    public CommentDeleteModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}