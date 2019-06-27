package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;

public class ItalianDoughDecorator extends AbstractDecorator implements Product {

    public ItalianDoughDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 4.0 + super.getPrice();
    }

}
