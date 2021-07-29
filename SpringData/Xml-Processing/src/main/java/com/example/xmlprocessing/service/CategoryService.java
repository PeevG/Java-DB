package com.example.xmlprocessing.service;

import com.example.xmlprocessing.models.dtos.CategorySeedDto;
import com.example.xmlprocessing.models.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categorySeedDto);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
