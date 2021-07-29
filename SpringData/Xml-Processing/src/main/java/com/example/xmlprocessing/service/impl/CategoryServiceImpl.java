package com.example.xmlprocessing.service.impl;

import com.example.xmlprocessing.models.dtos.CategorySeedDto;
import com.example.xmlprocessing.models.entity.Category;
import com.example.xmlprocessing.repository.CategoryRepository;
import com.example.xmlprocessing.service.CategoryService;
import com.example.xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories) {
        categories
                .stream()
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public long getEntityCount() {
        return categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories() {

        Set<Category> categories = new HashSet<>();
        long categoriesCount = categoryRepository.count();
        for (int i = 0; i < 2; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, categoriesCount);

            categories.add(categoryRepository.findById(randomId)
            .orElse(null));
        }
        return categories;
    }
}