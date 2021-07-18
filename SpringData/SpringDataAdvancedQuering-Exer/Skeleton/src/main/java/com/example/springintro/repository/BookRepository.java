package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal priceUnder, BigDecimal priceOver);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDateBefore, LocalDate releaseAfter);

    List<Book> findAllByTitleContaining(String title);

    //@Query("SELECT b FROM Book b WHERE b.author.lastName LIKE CONCAT('%', :lNameStartWith)")
    List<Book> findAllByAuthorLastNameStartsWith(String lNameStartWith);

    @Query("SELECT count(b) FROM Book b WHERE LENGTH(b.title) > :titleLength")
    int countOfBookWithTitleLengthMoreThan(@Param(value = "titleLength") int titleLength);

    List<Book> findBookByTitle(String title);
}
