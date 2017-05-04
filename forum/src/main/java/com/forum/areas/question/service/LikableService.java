package com.forum.areas.question.service;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('USER')")
public interface LikableService {
    Boolean like(Long id);

    Boolean dislike(Long id);
}