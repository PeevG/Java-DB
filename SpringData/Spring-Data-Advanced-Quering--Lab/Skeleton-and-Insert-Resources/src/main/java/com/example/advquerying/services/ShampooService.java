package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public interface ShampooService  {

    List<Shampoo> findAllShampooBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long label_id);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceIsLessThan(BigDecimal price);

    List<String> findAllByIngredientsNames(List<String> ingredientsNames);

    List<Shampoo> findAllShampooByeIngredientsCount(Integer ingredientsCount);


}
