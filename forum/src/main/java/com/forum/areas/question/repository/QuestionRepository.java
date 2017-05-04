package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @PreAuthorize("hasRole('USER')")
    @Modifying
    @Query(value = "DELETE FROM users_questions" +
            " WHERE question_id = :questionId", nativeQuery = true)
    void deleteRelationWithUser(@Param("questionId") Long questionId);

    @Query(value = "SELECT * FROM questions AS q" +
            " JOIN questions_tags AS qt ON q.id = qt.question_id" +
            " WHERE qt.tag_id = :tagId", nativeQuery = true)
    List<Question> findQuestionsByTag(@Param("tagId") Long tagId);

    @Query(value = "SELECT * FROM questions AS q" +
            " WHERE q.category_id = :categoryId",nativeQuery = true)
    List<Question> findQuestionsByCategory(@Param("categoryId") Long categoryId);
}