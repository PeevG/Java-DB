package com.example.xmlprocessing;

import com.example.xmlprocessing.models.dtos.CategorySeedRootDto;
import com.example.xmlprocessing.models.dtos.ProductSeedRootDto;
import com.example.xmlprocessing.models.dtos.UserSeedRootDto;
import com.example.xmlprocessing.service.CategoryService;
import com.example.xmlprocessing.service.ProductService;
import com.example.xmlprocessing.service.UserService;
import com.example.xmlprocessing.util.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.xml";
    private static final String USERS_FILE_PATH = "src/main/resources/files/users.xml";
    private static final String PRODUCTS_FILE_PATH = "src/main/resources/files/products.xml";

    public CommandLineRunner(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (categoryService.getEntityCount() == 0) {

            CategorySeedRootDto categorySeedRootDto = xmlParser.fromFile(CATEGORIES_FILE_PATH, CategorySeedRootDto.class);

            categoryService.seedCategories(categorySeedRootDto.getCategories());

        }
        if (userService.getCount() == 0) {

            UserSeedRootDto userSeedRootDto = xmlParser.fromFile(USERS_FILE_PATH, UserSeedRootDto.class);

            userService.seedUsers(userSeedRootDto.getUsers());
        }
        if(productService.getCount() == 0) {

            ProductSeedRootDto productSeedRootDto = xmlParser.
                    fromFile(PRODUCTS_FILE_PATH, ProductSeedRootDto.class);

            productService.seedProducts(productSeedRootDto.getProducts());
        }
    }
}
