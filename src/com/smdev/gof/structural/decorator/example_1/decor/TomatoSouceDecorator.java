package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class TomatoSouceDecorator extends AbstractDecorator {

    public TomatoSouceDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.3 + super.getPrice();
    }

}
