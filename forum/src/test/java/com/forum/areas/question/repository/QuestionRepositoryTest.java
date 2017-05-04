package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Category;
import com.forum.areas.question.entity.Question;
import com.forum.areas.question.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findQuestionsByTag_shouldReturnCorrectCollection() throws Exception {
        // Arrange
        Tag tag = new Tag();
        tag.setTag("test");
        Question question = new Question();
        question.getTags().add(tag);

        this.tagRepository.save(tag);
        this.questionRepository.save(question);

        // Act & Assert
        List<Question> questionsByTag = this.questionRepository.findQuestionsByTag(tag.getId());
        assertEquals(1, questionsByTag.size());
    }

    @Test
    public void findQuestionsByTag_nonexistentTag_shouldReturnEmptyCollection() throws Exception {
        // Act
        List<Question> questionsByTag = this.questionRepository.findQuestionsByTag(1L);

        // Assert
        assertEquals(0, questionsByTag.size());
    }

    @Test
    public void findQuestionsByCategory() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategory("test");
        Question question = new Question();
        question.setCategory(category);

        this.categoryRepository.save(category);
        this.questionRepository.save(question);

        // Act & Assert
        List<Question> questionsByTag =
                this.questionRepository.findQuestionsByCategory(category.getId());
        assertEquals(1, questionsByTag.size());
    }

    @Test
    public void findQuestionsByCategory_nonexistentCategory_shouldReturnEmptyCollection() throws Exception {
        // Act
        List<Question> questionsByCategory = this.questionRepository.findQuestionsByCategory(1L);

        // Assert
        assertEquals(0, questionsByCategory.size());
    }
}