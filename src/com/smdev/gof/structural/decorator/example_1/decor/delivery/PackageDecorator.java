package com.smdev.gof.structural.decorator.example_1.decor.delivery;

import com.smdev.gof.structural.decorator.example_1.Product;
import com.smdev.gof.structural.decorator.example_1.decor.AbstractDecorator;

public class PackageDecorator extends AbstractDecorator {

    public PackageDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.8 + super.getPrice();
    }

}
