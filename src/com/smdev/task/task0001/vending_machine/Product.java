package com.smdev.task.task0001.vending_machine;

import lombok.Getter;

import java.math.BigDecimal;

public enum Product {
    CROISSANT(1, BigDecimal.valueOf(1), "Croissant"),
    MUFFIN(2, BigDecimal.valueOf(0.5), "Muffin"),
    PIZZA(3, BigDecimal.valueOf(1.5), "Pizza"),

    ;
    /**
     * The value
     */
    @Getter
    private int buttonNo;
    /**
     * The value
     */
    @Getter
    private BigDecimal price;

    /**
     * In millimeters
     */
    @Getter
    private String label;

    Product(int buttonNo, BigDecimal price, String label) {
        this.buttonNo = buttonNo;
        this.price = price;
        this.label = label;
    }

    public static Product getByButton(int buttonNo) {
        Product[] products = Product.values();
        for (Product p : products) {
            if (buttonNo == p.getButtonNo()) {
                return p;
            }
        }
        return null;
    }

}
