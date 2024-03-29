package com.example.advquerying.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findAllShampooBySizeOrderById(Size size) {
        return this.shampooRepository.findAllShampooBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long label_id) {
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size, label_id);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findAllByPriceIsLessThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceIsLessThan(price);
    }

    @Override
    public List<String> findAllByIngredientsNames(List<String> ingredientsNames) {
        return this.shampooRepository
                .findAllByIngredientsNames(ingredientsNames)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shampoo> findAllShampooByeIngredientsCount(Integer ingredientsCount) {
        return this.shampooRepository
                .findAllShampooByeIngredientsCount(ingredientsCount);
    }


}
