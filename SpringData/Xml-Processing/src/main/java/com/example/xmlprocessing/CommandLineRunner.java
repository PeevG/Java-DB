package com.example.xmlprocessing;

import com.example.xmlprocessing.models.dtos.CategorySeedRootDto;
import com.example.xmlprocessing.service.CategoryService;
import com.example.xmlprocessing.util.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.xml";

    public CommandLineRunner(XmlParser xmlParser, CategoryService categoryService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        CategorySeedRootDto categorySeedRootDto = xmlParser.fromFile(CATEGORIES_FILE_PATH, CategorySeedRootDto.class);

        categoryService.seedCategories(categorySeedRootDto.getCategories());
    }
}
