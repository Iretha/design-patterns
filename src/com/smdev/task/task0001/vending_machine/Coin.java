package com.smdev.task.task0001.vending_machine;

import lombok.Getter;

import java.math.BigDecimal;

public enum Coin {
    /**
     * 10 cents
     */
    CENTS_10(BigDecimal.valueOf(0.1), 1.35, 2.268),
    /**
     * 25 cents
     */
    CENTS_25(BigDecimal.valueOf(0.25), 1.75, 5.67),
    /**
     * 50 cents
     */
    CENTS_50(BigDecimal.valueOf(0.5), 2.15, 11.34),
    /**
     * 1 dollar
     */
    DOLLAR_1(BigDecimal.valueOf(1.0), 2.58, 22.68),

    ;
    /**
     * The value
     */
    @Getter
    private BigDecimal nominal;

    /**
     * In millimeters
     */
    @Getter
    private double diameter;

    /**
     * In grams
     */
    @Getter
    private double weight;

    Coin(BigDecimal nominal, double diameter, double weight) {
        this.nominal = nominal;
        this.diameter = diameter;
        this.weight = weight;
    }

    /**
     * Acts as an abstract factory
     *
     * @param diameter
     * @param weight
     * @return
     */
    public static Coin get(double diameter, double weight) {
        Coin[] coins = Coin.values();
        for(Coin coin: coins){
            if(coin.getDiameter() == diameter && coin.getWeight() == weight){
                return coin;
            }
        }
        return null;
    }
}
