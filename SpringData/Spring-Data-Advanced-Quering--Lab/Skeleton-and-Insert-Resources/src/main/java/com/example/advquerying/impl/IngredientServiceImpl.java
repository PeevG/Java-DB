package com.example.advquerying.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameIsStartingWith(String name) {
        return this.ingredientRepository.findAllByNameIsStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameInOrderByPrice(Collection<String> name) {
        return this.findAllByNameInOrderByPrice(name);
    }

    @Override
    @Transactional
    public void deleteIngredientsByName(String name) {
        this.ingredientRepository
                .deleteIngredientsByName(name);
    }

    @Override
    @Transactional
    public void updateIngredientsPrice() {
        this.ingredientRepository
                .updateIngredientsPrice();
    }

    @Override
    @Transactional
    public void updateIngredientPriceByName(String name) {
        this.ingredientRepository
                .updateIngredientPriceByName(name);
    }


}
