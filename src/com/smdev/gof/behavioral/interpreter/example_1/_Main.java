package com.smdev.gof.behavioral.interpreter.example_1;

import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

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
        data.put("dogs", dogs);

        Context context = new Context(data);
        try {
            Context ctx = Interpreter.interpret("find dogs where breed eq german_shepard and gender eq female", context);

            List<Dog> result = ctx.evaluate();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
