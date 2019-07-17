package com.smdev.structural.decorator.example_1;

import com.smdev.structural.decorator.example_1.decor.delivery.DeliveryDecorator;
import com.smdev.structural.decorator.example_1.decor.delivery.PackageDecorator;
import com.smdev.structural.decorator.example_1.decor.food.*;

public class _Main {

    public static void main(String[] args) {

        Product pizza1 = new ItalianDoughDecorator(new TomatoSouceDecorator(new SalamiDecorator(new MushroomDecorator(new CheeseDecorator(new BbqSouceDecorator(new Pizza()))))));
        System.out.println("=> Total Price: " + pizza1.getPrice());
        System.out.println("");

        Product pizza2 = new RegularDoughDecorator(new TomatoSouceDecorator(new MushroomDecorator(new MushroomDecorator(new CheeseDecorator(new BbqSouceDecorator(new Pizza()))))));
        System.out.println("=> Total Price: " + pizza2.getPrice());
        System.out.println("");


        Product pizzaForHome = new PackageDecorator(pizza1);
        System.out.println("=> Total Price: " + pizzaForHome.getPrice());
        System.out.println("");


        Product pizzaForHomeWithDelivery = new DeliveryDecorator(new PackageDecorator(pizza2));
        System.out.println("=> Total Price: " + pizzaForHomeWithDelivery.getPrice());
        System.out.println("");
    }
}
