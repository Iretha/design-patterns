package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Coin {

    /** In millimeters */
    @Getter
    private double diameter;

    /** In grams */
    @Getter
    private double weight;

    public Coin(double diameter, double weight) {
        this.diameter = diameter;
        this.weight = weight;
    }
}
