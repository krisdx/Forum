package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    private static final String TEST_CATEGORY_NAME = "test";

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findExistingCategory_shouldReturnCategory() throws Exception {
        // Arrange
        Category expectedCategory = new Category();
        expectedCategory.setCategory(TEST_CATEGORY_NAME);

        // Act
        this.categoryRepository.save(expectedCategory);
        Category actualCategory =
                this.categoryRepository.findByCategory(TEST_CATEGORY_NAME);

        // Assert
        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void findNonExistingCategory_shouldReturnNull() throws Exception {
        // Act
        Category actualCategory =
                this.categoryRepository.findByCategory(TEST_CATEGORY_NAME);

        // Assert
        assertNull(actualCategory);
    }
}