package com.forum.areas.question.service;

import com.forum.areas.question.entity.Category;
import com.forum.areas.question.model.view.CategoryViewModel;
import com.forum.areas.question.repository.CategoryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @After
    public void after(){
        this.categoryRepository.deleteAll();
    }

    @Test
    public void findAllViewModels_shouldReturnCorrectCollection() throws Exception {
        // Arrange & Act
        final int categoriesCount = 2;
        List<CategoryViewModel> expectedCategoryViewModels = new ArrayList<>();
        for (int i = 1; i <= categoriesCount; i++) {
            Category category = new Category();
            category.setCategory("category " + i);
            this.categoryRepository.save(category);
            expectedCategoryViewModels.add(this.modelMapper.map(category, CategoryViewModel.class));
        }

        List<CategoryViewModel> actualCategoryViewModels = this.categoryService.findAllViewModels();

        // Assert
        assertEquals(expectedCategoryViewModels.size(), actualCategoryViewModels.size());
        for (int i = 0; i < expectedCategoryViewModels.size(); i++) {
            assertThat(actualCategoryViewModels.get(i), samePropertyValuesAs(expectedCategoryViewModels.get(i)));
        }
    }

    @Test
    public void findAllViewModels_noCategories_shouldReturnEmptyCollection() throws Exception {
        // Act
        List<CategoryViewModel> actualCategoryViewModels = this.categoryService.findAllViewModels();

        // Assert
        assertEquals(0, actualCategoryViewModels.size());
    }

    @Test
    public void findByCategoryName_shouldReturnCorrectCategory() throws Exception {
        // Arrange
        final String CATEGORY_NAME = "test";
        Category expectedCategory = new Category();
        expectedCategory.setCategory(CATEGORY_NAME);
        this.categoryRepository.save(expectedCategory);

        // Act
        Category actualCategory = this.categoryService.findByCategoryName(CATEGORY_NAME);

        // Assert
        assertThat(actualCategory, samePropertyValuesAs(expectedCategory));
    }

    @Test
    public void findByCategoryName_nonExistingCategory_shouldReturnNull() throws Exception {
        // Act
        String randomCategoryName = "123";
        Category actualCategory = this.categoryService.findByCategoryName(randomCategoryName);

        // Assert
        assertNull(actualCategory);
    }
}