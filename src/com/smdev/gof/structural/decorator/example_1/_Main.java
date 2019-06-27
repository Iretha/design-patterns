package com.smdev.gof.structural.decorator.example_1;

import com.smdev.gof.structural.decorator.example_1.decor.*;

public class _Main {

    public static void main(String[] args) {

        Product pizza1 = new ItalianDoughDecorator(new TomatoSouceDecorator(new SalamiDecorator(new MushroomDecorator(new CheeseDecorator(new Pizza())))));
        System.out.println("=> Total Price: " + pizza1.getPrice());
        System.out.println("");

        Product pizza2 = new RegularDoughDecorator(new TomatoSouceDecorator(new MushroomDecorator(new MushroomDecorator(new CheeseDecorator(new BbqSouceDecorator(new Pizza()))))));
        System.out.println("=> Total Price: " + pizza2.getPrice());
        System.out.println("");
    }
}
