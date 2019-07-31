package com.smdev.task.task0001.vending_machine;

import lombok.Getter;

import java.math.BigDecimal;

public enum ProductType {
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

    ProductType(int buttonNo, BigDecimal price, String label) {
        this.buttonNo = buttonNo;
        this.price = price;
        this.label = label;
    }

    public static ProductType getByButton(int buttonNo) {
        ProductType[] products = ProductType.values();
        for (ProductType p : products) {
            if (buttonNo == p.getButtonNo()) {
                return p;
            }
        }
        return null;
    }

}
