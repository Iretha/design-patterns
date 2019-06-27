package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class SalamiDecorator extends AbstractDecorator implements Product {

    public SalamiDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 2.69 + super.getPrice();
    }

}
