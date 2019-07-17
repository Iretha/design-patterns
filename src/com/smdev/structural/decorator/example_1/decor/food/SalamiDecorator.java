package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class SalamiDecorator extends AbstractDecorator {

    public SalamiDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 2.69 + super.getPrice();
    }

}
