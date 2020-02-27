package com.smdev.task.task0001.vendingmachine.balance;

import com.smdev.task.task0001.vendingmachine.Item;
import lombok.Getter;

/**
 * All coin types
 */
public enum Coin implements Item {
    /**
     * PENNY
     */
    CENTS_1(1),
    /**
     * NICKEL
     */
    CENTS_5(5),
    /**
     * DIME
     */
    CENTS_10(10),
    /**
     * QUARTER
     */
    CENTS_25(25),
    /**
     * 50 CENTS
     */
    CENTS_50(50),
    ;

    /**
     * In cents
     */
    @Getter
    private int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }
}
