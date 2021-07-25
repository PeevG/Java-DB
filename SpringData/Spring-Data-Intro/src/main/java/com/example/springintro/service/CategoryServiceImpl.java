package com.example.springintro.service;

import com.example.springintro.entity.Category;
import com.example.springintro.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORY_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(row -> {
                    Category category = new Category(row);

                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomInt = ThreadLocalRandom
                .current().nextInt(1, 3);
        long catRepoCount = categoryRepository.count();

        for (int i = 0; i < randomInt; i++) {
            long randomId = ThreadLocalRandom
                    .current()
                    .nextLong(1, catRepoCount + 1);

            Category category = categoryRepository
                    .findById(randomId)
                    .orElse(null);

            categories.add(category);
        }
        return categories;
    }
}
