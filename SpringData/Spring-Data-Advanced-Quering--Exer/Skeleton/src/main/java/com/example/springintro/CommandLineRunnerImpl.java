package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        // We use skeleton from previous project, so here is some unused old methods. :)

        // printAllBooksAfterYear(2000);
        // printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        // printAllAuthorsAndNumberOfTheirBooks();
        // printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");


        System.out.println();
        System.out.println("Please enter exercise number: ");
        int exerciseNumber = Integer.parseInt(bufferedReader.readLine());

        switch (exerciseNumber) {
            case 1 -> booksTitleByAgeRestriction();
            case 2 -> goldenBooks();
            case 3 -> booksByPrice();
            case 4 -> notReleasedBooks();
            case 5 -> booksReleasedBefore();
            case 6 -> authorsSearch();
            case 7 -> booksSearch();
            case 8 -> booksTitleSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
            case 11 -> reducedBook();
        }
    }

    private void reducedBook() throws IOException {
        System.out.println("Please enter title: ");
        String title = bufferedReader.readLine();

        this.bookService
                .printInfoAboutBookByGivenTitle(title)
                .forEach(System.out::println);
    }

    private void totalBookCopies() {
        this.authorService
                .findAuthorTotalBookCopies()
                .forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println("Please enter a number of characters in title: ");
        int numberOfCharacters = Integer.parseInt(bufferedReader.readLine());

        int allBooksByTitleLength = this.bookService
                .findAllBooksByTitleLength(numberOfCharacters);
        System.out.printf("There are %d books with longer title than %d symbols.", allBooksByTitleLength, numberOfCharacters);
    }

    private void booksTitleSearch() throws IOException {
        System.out.println("Please enter last name starts with: ");
        String lNameStartWith = bufferedReader.readLine();

        this.bookService
                .findAllBooksByAuthorsWhoseLastNameStartsWith(lNameStartWith)
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println("Please enter part from title you looking for:");
        String partOfTitle = bufferedReader.readLine();

        this.bookService
                .findAllBooksIfTheyContaining(partOfTitle)
                .forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println("Please enter first name ends with: ");
        String firstNameEndWith = bufferedReader.readLine();
        this.authorService
                .findAllAuthorsWhoseFirstNameEndWith(firstNameEndWith)
                .forEach(System.out::println);
    }

    private void booksReleasedBefore() throws IOException {
        System.out.println("Please enter date in format dd-MM-yyyy.");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        this.bookService
                .findAllBooksBeforeDate(localDate)
                .forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println("Please enter year of release: ");
        int year = Integer.parseInt(bufferedReader.readLine());
        LocalDate dateBefore = LocalDate.of(year, 1, 1);
        LocalDate dateAfter = LocalDate.of(year, 12, 31);

        this.bookService
                .findAllByReleaseDateBeforeOrReleaseDateAfter(dateBefore, dateAfter)
                .forEach(System.out::println);


    }

    private void booksByPrice() throws IOException {

        System.out.println("Please enter price under you looking for: ");
        BigDecimal underPrice = new BigDecimal(bufferedReader.readLine());
        System.out.println("Please enter price over you looking for: ");
        BigDecimal overPrice = new BigDecimal(bufferedReader.readLine());
        this.bookService
                .findAllByPriceIsLessThanOrPriceGreaterThan(underPrice, overPrice)
                .forEach(System.out::println);
    }

    private void goldenBooks() {
        bookService.findAllByEditionTypeAndCopiesIsLessThan()
                .forEach(System.out::println);
    }

    private void booksTitleByAgeRestriction() throws IOException {
        System.out.println("Please select age restriction: Minor, Teen or Adult.");

        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());

        bookService.findAllBookTitlesWithAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
