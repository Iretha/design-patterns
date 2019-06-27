package com.smdev.gof.structural.decorator.example_1.decor;

import com.smdev.gof.structural.decorator.example_1.Product;
import lombok.Getter;

public abstract class AbstractDecorator implements Product {

    @Getter
    private Product base;

    public AbstractDecorator(Product base) {
        this.base = base;
    }

    @Override
    public double getPrice() {
        System.out.println("Adding " + getClass().getSimpleName());
        return this.base != null ? this.base.getPrice() : 0;
    }
}
