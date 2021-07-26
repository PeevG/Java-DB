package com.example.jsonprocessing.service.impl;

import com.example.jsonprocessing.model.dto.ProductNameAndPriceDto;
import com.example.jsonprocessing.model.dto.ProductSeedDto;
import com.example.jsonprocessing.model.entity.Product;
import com.example.jsonprocessing.repository.ProductRepository;
import com.example.jsonprocessing.service.CategoryService;
import com.example.jsonprocessing.service.ProductService;
import com.example.jsonprocessing.service.UserService;
import com.example.jsonprocessing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private static final String PATH_FOR_PRODUCTS_FILE = "src/main/resources/files/products.json";

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, UserService userService, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedProducts() throws IOException {
        if(productRepository.count() > 0) {
            return;
        }

        String fileContent = Files.readString(Path.of(PATH_FOR_PRODUCTS_FILE));

        ProductSeedDto[] productSeedDto = gson.fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDto)
                .filter(validationUtil::isValid)
                .map(productSeedDto1 -> {
                    Product product = modelMapper.map(productSeedDto1, Product.class);
                    product.setSeller(userService.findRandomUser());
                    if(product.getPrice().compareTo(BigDecimal.valueOf(900L)) > 0) {
                        product.setBuyer(userService.findRandomUser());
                    }
                    product.setCategories(categoryService.findRandomCategories());

                   return product;
                })
                .forEach(productRepository::save);

    }

    @Override
    public List<ProductNameAndPriceDto> findAllProductsInRangeOrderByPrice(BigDecimal lowerBound, BigDecimal upperBound) {
        return productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowerBound, upperBound)
                .stream()
                .map(product -> {
                    ProductNameAndPriceDto productNameAndPriceDto = modelMapper.map(product, ProductNameAndPriceDto.class);

                    productNameAndPriceDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productNameAndPriceDto;
                })
                .collect(Collectors.toList());
    }
}
