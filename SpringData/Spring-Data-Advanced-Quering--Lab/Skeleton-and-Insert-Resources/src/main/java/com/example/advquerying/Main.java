package com.example.advquerying;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main implements CommandLineRunner {

    //Todo: Set your username and password in application.properties

    private final ShampooService shampooService;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;
    Scanner scanner = new Scanner(System.in);

    public Main(ShampooService shampooService, IngredientRepository ingredientRepository, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println("Please enter exercise number: ");
        int exerciseNumber = Integer.parseInt(scanner.nextLine());

        switch (exerciseNumber) {
            case 1 -> exerOne();
            case 2 -> exerTwo();
            case 3 -> exerThree();
            case 4 -> exerFour();
            case 5 -> exerFive();
            case 6 -> exerSix();
            case 7 -> exerSeven();
            case 8 -> exerEight();
            case 9 -> exerNine();
            case 10 -> exerTen();
            case 11 -> exerEleven();
        }
    }

    private void exerEleven() {
        System.out.println("Please enter ingredient name to update its price!");
        String ingrToIncrease = scanner.nextLine();
        this.ingredientService.updateIngredientPriceByName(ingrToIncrease);
    }

    private void exerTen() {
        System.out.println("You have increased price of all ingredients by 10%!");
        this.ingredientService.updateIngredientsPrice();
    }

    private void exerNine() {

        System.out.println("Please enter ingredient name to delete: ");
        String nameToDelete = scanner.nextLine();

        this.ingredientService
                .deleteIngredientsByName(nameToDelete);
    }

    private void exerEight() {
        System.out.println("Please enter ingredients count: ");

        int ingrCount = Integer.parseInt(scanner.nextLine());

        List<Shampoo> allShampooByeIngredientsCount = this.shampooService.findAllShampooByeIngredientsCount(ingrCount);
        allShampooByeIngredientsCount.forEach(System.out::println);
    }

    private void exerSeven() {
        System.out.println("Please enter ingredients names, when you finish type 'find' ");
        List<String> ingredientsNames = new ArrayList<>();

        while (!scanner.nextLine().equals("find")) {
            String ingredientName = scanner.nextLine();
            ingredientsNames.add(ingredientName);
        }

        this.shampooService
                .findAllByIngredientsNames(ingredientsNames)
                .forEach(System.out::println);
    }

    private void exerSix() {
        System.out.println("Please enter price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        int shampooCount = this.shampooService.findAllByPriceIsLessThan(price)
                .size();
        System.out.println(shampooCount);
    }

    private void exerFive() {
        System.out.println("Enter names on one line separated by space: ");
        List<String> names = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        this.ingredientRepository.findAllByNameInOrderByPrice(names)
                .forEach(System.out::println);
    }

    private void exerFour() {

        System.out.println("Please enter a letter: ");

        String letter = scanner.nextLine();

        this.ingredientRepository.findAllByNameIsStartingWith(letter)
                .forEach(System.out::println);
        System.out.println();
    }

    private void exerThree() {

        System.out.println("Please enter a price: ");

        BigDecimal price = new BigDecimal(scanner.nextLine());

        this.shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(System.out::println);
    }

    private void exerTwo() {
        System.out.println("Please enter size: SMALL, MEDIUM or LARGE");
        Size size = Size.valueOf(scanner.nextLine());
        System.out.println("Please pick label id: ");
        Long labelId = Long.parseLong(scanner.nextLine());

        this.shampooService.findAllBySizeOrLabelIdOrderByPrice(size, labelId)
                .forEach(System.out::println);
    }

    private void exerOne() {
        System.out.println("Please enter size: SMALL, MEDIUM or LARGE");
        Size size = Size.valueOf(scanner.nextLine());
        this.shampooService.findAllShampooBySizeOrderById(size)
                .forEach(System.out::println);
    }
}
