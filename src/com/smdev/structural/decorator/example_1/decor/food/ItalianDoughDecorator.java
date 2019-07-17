package com.smdev.structural.decorator.example_1.decor.food;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class ItalianDoughDecorator extends AbstractDecorator {

    public ItalianDoughDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 4.0 + super.getPrice();
    }

}
