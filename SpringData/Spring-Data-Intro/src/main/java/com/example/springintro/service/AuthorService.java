package com.example.springintro.service;

import com.example.springintro.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    Author gerRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
