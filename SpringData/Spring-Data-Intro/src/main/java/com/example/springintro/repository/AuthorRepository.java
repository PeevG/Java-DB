package com.example.springintro.repository;

import com.example.springintro.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a order by a.books.size")
    List<Author> findAllByBooksSizeDesc();
}
