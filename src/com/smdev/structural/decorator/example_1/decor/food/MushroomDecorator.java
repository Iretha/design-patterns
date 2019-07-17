package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class MushroomDecorator extends AbstractDecorator {

    public MushroomDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.5 + super.getPrice();
    }

}
