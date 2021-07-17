package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllByNameIsStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(Collection<String> name);

    void deleteIngredientsByName(String name);

    void updateIngredientsPrice();

    void updateIngredientPriceByName(String name);
}
