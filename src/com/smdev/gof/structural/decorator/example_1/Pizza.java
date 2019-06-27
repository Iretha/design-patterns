package com.smdev.gof.structural.decorator.example_1;

public class Pizza implements Product {

    @Override
    public double getPrice() {
        return 0.6; // base price
    }
}
