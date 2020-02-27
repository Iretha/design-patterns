package com.smdev.task.task0001.vendingmachine.product;

import com.smdev.task.task0001.vendingmachine.Item;
import lombok.Getter;

public enum Product implements Item {

    WATER(27),
    SODA(75),
    PEPSI(75),
    ORANGE_JUICE(64),
    ICED_TEA(90),
    ;

    /**
     * Price in cents
     */
    @Getter
    private int price;

    Product(int price) {
        this.price = price;
    }

}
