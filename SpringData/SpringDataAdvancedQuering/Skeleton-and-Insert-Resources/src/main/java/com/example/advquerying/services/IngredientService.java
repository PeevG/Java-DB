package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllByNameIsStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(Collection<String> name);
}
