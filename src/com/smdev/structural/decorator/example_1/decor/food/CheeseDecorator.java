package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class CheeseDecorator extends AbstractDecorator {

    public CheeseDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 1.17 + super.getPrice();
    }

}
