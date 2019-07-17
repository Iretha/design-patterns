package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class TomatoSouceDecorator extends AbstractDecorator {

    public TomatoSouceDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.3 + super.getPrice();
    }

}
