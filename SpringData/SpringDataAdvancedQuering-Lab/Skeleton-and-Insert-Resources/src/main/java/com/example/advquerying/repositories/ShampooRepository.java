package com.example.advquerying.repositories;

import com.example.advquerying.entities.BaseEntity;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends BaseRepository<Shampoo>{

    List<Shampoo> findAllShampooBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long label_id);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceIsLessThan(BigDecimal price);
}
