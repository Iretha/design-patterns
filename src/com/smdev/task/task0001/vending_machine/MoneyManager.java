package com.smdev.task.task0001.vending_machine;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyManager {

    @Getter(AccessLevel.PRIVATE)
    private Balance balance;

    @Getter(AccessLevel.PRIVATE)
    private Balance customerBalance;

    public MoneyManager(Map<Coin, Integer> initialBalance) {
        this.balance = new Balance(initialBalance);
        this.customerBalance = new Balance();
    }

    public void addToCustomerBalance(Coin coin) {
        getCustomerBalance().add(coin);
    }

    public void releaseCustomerBalance() {
        getCustomerBalance().releaseCoins();
    }

    public double getCustomerAmount() {
        return getCustomerBalance().getAmount();
    }

    public void acquireFromCustomerBalance(BigDecimal amount) throws Exception {
        if (amount == null || amount.doubleValue() == 0) {
            return;
        }

        double amountDbl = amount.doubleValue();
        double balanceDbl = getCustomerBalance().getAmount();
        if (amountDbl > balanceDbl) {
            throw new Exception("Amount not enough!");
        }
//TODO
////        Map<Coin, Integer> allCoins = Map.copyOf(getBalance().getCoins());
////        getCustomerBalance().getCoins().forEach((k, v) -> allCoins.put(k, allCoins.getOrDefault(k, 0) + v));
//
//        // can be calculated based on some rule
//        CoinStrategy strategy = new CoinStrategySimple();
//        Map<Coin, Integer> amountInCoins = strategy.convertInCoins(allCoins, amount);
//
//        getCustomerBalance().withdraw(amountInCoins);
//        getBalance().deposit(amountInCoins);
//        releaseCustomerBalance();
    }
}
