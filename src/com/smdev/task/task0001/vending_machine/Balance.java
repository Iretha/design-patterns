package com.smdev.task.task0001.vending_machine;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balance {

    @Getter(AccessLevel.PRIVATE)
    private Map<Coin, Integer> slots;

    private BigDecimal balance;
    private BigDecimal purchaseBalance;

    public Balance() {
        init();
    }

    private void init() {
        this.slots = new HashMap<>();
        this.balance = BigDecimal.valueOf(0);
        this.purchaseBalance = BigDecimal.valueOf(0);
    }

    public void addCoin(Coin coin) {
        Integer count = getCount(coin);
        getSlots().put(coin, ++count);

        this.purchaseBalance = this.purchaseBalance.add(coin.getNominal());
    }

    private Integer getCount(Coin coin) {
        Integer count = getSlots().get(coin);
        return count != null ? count : 0;
    }

    public BigDecimal getPurchaseBalance() {
        return BigDecimal.valueOf(this.purchaseBalance.doubleValue()); // we don't want to return the original value
    }

    public void makePurchase(BigDecimal value) throws Exception {
        if (value.doubleValue() > this.purchaseBalance.doubleValue()) {
            throw new Exception("Please, insert more coins to make the purchase!");
        }

        this.balance = this.balance.add(value);
        this.purchaseBalance = this.purchaseBalance.subtract(value);

        returnChange();
    }

    public List<Coin> returnChange() {
        if (this.purchaseBalance.doubleValue() > 0) {
            // TODO calc
        }
        return new ArrayList();
    }

}
