package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class CheeseDecorator extends AbstractDecorator {

    public CheeseDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 1.17 + super.getPrice();
    }

}
