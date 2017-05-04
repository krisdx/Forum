package com.forum.areas.question.model.view;

import java.io.Serializable;

public class TagViewModel implements Serializable {

    private Long id;
    private String tag;

    public TagViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}