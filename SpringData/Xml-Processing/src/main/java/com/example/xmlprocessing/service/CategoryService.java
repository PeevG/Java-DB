package com.example.xmlprocessing.service;

import com.example.xmlprocessing.models.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categorySeedDto);
}
