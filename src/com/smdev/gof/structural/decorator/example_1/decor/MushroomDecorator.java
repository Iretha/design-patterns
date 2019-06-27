package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class MushroomDecorator extends AbstractDecorator {

    public MushroomDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.5 + super.getPrice();
    }

}
