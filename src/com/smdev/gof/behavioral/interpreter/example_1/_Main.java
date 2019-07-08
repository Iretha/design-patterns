package com.smdev.gof.behavioral.interpreter.example_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _Main {

    public static void main(String[] args) {

        Map<String, List<Dog>> data = new HashMap<>();
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Ruby", "german_shepard", "female", 5));
        dogs.add(new Dog("Bobby", "german_shepard", "male", 3));
        dogs.add(new Dog("Rusty", "german_shepard", "male", 1));
        dogs.add(new Dog("Sara", "german_shepard", "female", 2));
        dogs.add(new Dog("Diva", "german_shepard", "female", 7));
        dogs.add(new Dog("Lila", "bulldog", "female", 8));
        dogs.add(new Dog("Lilo", "bulldog", "male", 12));
        dogs.add(new Dog("Viva", "pug", "female", 8));

        data.put("dogs", dogs);

        try {
            evaluate(data, "find dogs where gender eq male");
            evaluate(data, "find dogs where breed eq german_shepard and gender eq female");
            evaluate(data, "find dogs where breed eq german_shepard or breed eq bulldog and gender eq female");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void evaluate(Map<String, List<Dog>> data, String expression) throws Exception {
        System.out.println("------ Expression to evaluate: " + expression);
        Context context = new Context(data, expression);
        List<Dog> result = context.evaluate();
        System.out.println(result);
        System.out.println();
    }
}
