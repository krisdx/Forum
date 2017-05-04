package com.forum.areas.question.service;

import com.forum.areas.question.entity.Category;
import com.forum.areas.question.model.view.CategoryViewModel;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryViewModel> findAllViewModels();

    Category findByCategoryName(String category);
}