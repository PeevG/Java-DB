package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientRepository ingredientRepository;

    public Main(ShampooService shampooService, IngredientRepository ingredientRepository) {
        this.shampooService = shampooService;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //Todo: Pick exercise:

//        System.out.println("Exercise 1");
//        System.out.println();
//        System.out.println("Please enter size: SMALL, MEDIUM or LARGE");
//        Size size = Size.valueOf(scanner.nextLine());
//        this.shampooService.findAllShampooBySizeOrderById(size)
//                .forEach(System.out::println);
//
//        System.out.println();
//
//        System.out.println("Exercise 2");
//        System.out.println();
//        System.out.println("Please enter size: SMALL, MEDIUM or LARGE");
//        size = Size.valueOf(scanner.nextLine());
//        System.out.println("Please pick label id: ");
//        Long labelId = Long.parseLong(scanner.nextLine());
//
//        this.shampooService.findAllBySizeOrLabelIdOrderByPrice(size, labelId)
//                .forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("Exercise 3");
//        System.out.println();
//        System.out.println("Please enter a price: ");
//
//        BigDecimal price = new BigDecimal(scanner.nextLine());
//
//        this.shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price)
//                .forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("Exercise 4");
//        System.out.println();
//        System.out.println("Please enter a letter: ");
//
//        String letter = scanner.nextLine();
//
//        this.ingredientRepository.findAllByNameIsStartingWith(letter)
//                .forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("Exercise 5");
//        System.out.println();
//        System.out.println("Enter names on one line separated by space: ");
//        List<String> names = Arrays.stream(scanner.nextLine().split("\\s+"))
//                .collect(Collectors.toList());
//
//        this.ingredientRepository.findAllByNameInOrderByPrice(names)
//                .forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("Exercise 6");
//        System.out.println();
//        System.out.println("Please enter price: ");
//        price = new BigDecimal(scanner.nextLine());
//
//        int shampooCount = this.shampooService.findAllByPriceIsLessThan(price)
//                .size();
//        System.out.println(shampooCount);
//        System.out.println();

        System.out.println("Exercise 7");
        System.out.println();


    }
}
