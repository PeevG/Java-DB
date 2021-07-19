package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameIsStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(Collection<String> name);

    @Query("DELETE FROM Ingredient i WHERE i.name = :name")
    @Modifying
    void deleteIngredientsByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1")
    @Modifying
    void updateIngredientsPrice();

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1 WHERE i.name = :name")
    @Modifying
    void updateIngredientPriceByName(@Param(value = "name") String name);
}
