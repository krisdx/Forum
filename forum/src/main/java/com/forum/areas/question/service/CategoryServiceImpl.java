package com.forum.areas.question.service;

import com.forum.areas.question.entity.Category;
import com.forum.areas.question.model.view.CategoryViewModel;
import com.forum.areas.question.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryViewModel> findAllViewModels() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        for (Category category : categories) {
            CategoryViewModel categoryViewModel =
                    this.modelMapper.map(category, CategoryViewModel.class);
            categoryViewModels.add(categoryViewModel);
        }

        return categoryViewModels;
    }

    @Override
    public Category findByCategoryName(String category) {
        return this.categoryRepository.findByCategory(category);
    }
}