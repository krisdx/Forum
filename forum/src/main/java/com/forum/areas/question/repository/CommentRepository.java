package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @PreAuthorize("hasRole('USER')")
    @Modifying
    @Query(value = "DELETE FROM questions_comments" +
            " WHERE comment_id = :commentId", nativeQuery = true)
    void deleteRelationsWithQuestion(@Param("commentId") Long commentId);
}