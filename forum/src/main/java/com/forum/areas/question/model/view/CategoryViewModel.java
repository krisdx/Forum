package com.forum.areas.question.model.view;

import java.io.Serializable;

public class CategoryViewModel implements Serializable {

    private String category;

    public CategoryViewModel() {
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}