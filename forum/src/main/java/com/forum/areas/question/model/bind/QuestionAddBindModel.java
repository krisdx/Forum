package com.forum.areas.question.model.bind;

import com.forum.areas.question.validation.Tags;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class QuestionAddBindModel implements Serializable {

    @NotBlank(message = "The title cannot be empty.")
    private String title;

    @NotBlank(message = "The question cannot be empty.")
    private String question;

    private String category;

    @Tags
    @NotBlank(message = "There must be at least one valid tag.")
    private String tags;

    public QuestionAddBindModel() {
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}