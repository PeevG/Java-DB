package com.example.xmlprocessing.service;

import com.example.xmlprocessing.models.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {
    long getCount();

    void seedProducts(List<ProductSeedDto> products);
}
