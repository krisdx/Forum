package com.forum.areas.question.model.bind;

import java.io.Serializable;

@SuppressWarnings("SameParameterValue")
public class EditBindModel implements Serializable {

    private String editedText;

    public EditBindModel() {
    }

    public String getEditedText() {
        return this.editedText;
    }

    public void setEditedText(String editedText) {
        this.editedText = editedText;
    }
}