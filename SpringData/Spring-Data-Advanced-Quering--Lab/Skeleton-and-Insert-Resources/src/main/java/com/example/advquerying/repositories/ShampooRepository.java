package com.example.advquerying.repositories;

import com.example.advquerying.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends BaseRepository<Shampoo> {

    List<Shampoo> findAllShampooBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long label_id);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceIsLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :ingredientsNames")
    List<Shampoo> findAllByIngredientsNames(List<String> ingredientsNames);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE s.ingredients.size < :ingredientsCount")
    List<Shampoo> findAllShampooByeIngredientsCount(Integer ingredientsCount);


}
