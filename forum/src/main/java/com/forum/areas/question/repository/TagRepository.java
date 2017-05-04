package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTag(String tag);
}