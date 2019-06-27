package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class RegularDoughDecorator extends AbstractDecorator {

    public RegularDoughDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 2.0 + super.getPrice();
    }

}
