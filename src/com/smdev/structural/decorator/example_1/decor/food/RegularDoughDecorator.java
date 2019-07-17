package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class RegularDoughDecorator extends AbstractDecorator {

    public RegularDoughDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 2.0 + super.getPrice();
    }

}
