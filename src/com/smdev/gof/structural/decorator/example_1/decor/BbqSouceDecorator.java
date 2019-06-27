package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class BbqSouceDecorator extends AbstractDecorator {

    public BbqSouceDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.75 + super.getPrice();
    }

}
