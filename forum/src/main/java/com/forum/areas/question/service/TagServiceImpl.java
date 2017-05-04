package com.forum.areas.question.service;

import com.forum.areas.question.entity.Tag;
import com.forum.areas.question.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag findByTagName(String tagName) {
        return this.tagRepository.findByTag(tagName);
    }

    @Override
    public Tag createTagIfNotExists(String tagName) {
        Tag tag = this.tagRepository.findByTag(tagName);
        if (tag == null) {
            Tag newTag = new Tag();
            newTag.setTag(tagName);
            return this.tagRepository.save(newTag);
        }

        return tag;
    }
}