package com.forum.areas.question.service;

import com.forum.areas.question.entity.Tag;

public interface TagService {
    Tag findByTagName(String tag);

    Tag createTagIfNotExists(String tag);
}