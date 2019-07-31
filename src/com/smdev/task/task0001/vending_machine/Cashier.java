package com.smdev.task.task0001.vending_machine;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

public class Cashier {

    @Getter(AccessLevel.PRIVATE)
    private Balance balance;

    @Getter(AccessLevel.PRIVATE)
    private Balance customerBalance;

    public Cashier(Map<CoinType, Integer> initialBalance) {
        this.balance = new Balance(initialBalance);
        this.customerBalance = new Balance();
    }

    public void addToCustomerBalance(CoinType coin) {
        getCustomerBalance().add(coin);
    }

    public void releaseCustomerBalance() {
        getCustomerBalance().releaseCoins();
    }

    public double getCustomerAmount() {
        return getCustomerBalance().getAmount().doubleValue();
    }

    public void acquireFromCustomerBalance(BigDecimal amount) throws Exception {
        if (amount == null || amount.doubleValue() == 0) {
            return;
        }

        BigDecimal change = getCustomerBalance().getAmount().subtract(amount);
        if (change.doubleValue() < 0) {
            throw new Exception("Amount not enough!");
        }

        Map<CoinType, Integer> coins = getCustomerBalance().getCoins();
        getBalance().deposit(coins);
        getCustomerBalance().withdraw(coins);
        getBalance().withdrawChange(change);
    }
}
