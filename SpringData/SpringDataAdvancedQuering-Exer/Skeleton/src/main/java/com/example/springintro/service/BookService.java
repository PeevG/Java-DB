package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByEditionTypeAndCopiesIsLessThan();

    List<String> findAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal priceUnder, BigDecimal priceOver);

    List<String> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDateBefore, LocalDate releaseAfter);

    List<String> findAllBooksBeforeDate(LocalDate localDate);

    List<String> findAllBooksIfTheyContaining(String partOfTitle);
}
