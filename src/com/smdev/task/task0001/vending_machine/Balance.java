package com.smdev.task.task0001.vending_machine;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Balance {

    @Getter(AccessLevel.PACKAGE)
    private Map<Coin, Integer> coins;

    private BigDecimal amount;

    public Balance() {
        init();
    }

    public Balance(Map<Coin, Integer> initialCoins) {
        this();
        deposit(initialCoins);

        System.out.println("Initial Balance " + getAmount());
    }

    void init() {
        this.coins = new HashMap<>();
        this.amount = BigDecimal.valueOf(0);

        Coin[] coinTypes = Coin.values();
        for (Coin coinType : coinTypes) {
            if (!Coin.UNKNOWN.equals(coinType)) {
                this.coins.put(coinType, 0);
            }
        }
    }

    public void deposit(Map<Coin, Integer> coins) {
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void add(Coin coin) {
        add(coin, 1);
    }

    private void add(Coin coin, Integer count) {
        if (coin == null) {
            return;
        }

        this.coins.put(coin, this.coins.get(coin) + count);
        this.amount = this.amount.add(BigDecimal.valueOf(count).multiply(coin.getNominal()));
    }

    public double getAmount() {
        return this.amount.doubleValue();
    }

    public void releaseCoins() {
        double currentBalance = this.amount.doubleValue();
        if (currentBalance == 0) {
            System.out.println("Nothing to release...");
        } else {
            System.out.println("Releasing " + currentBalance);
            init();
        }
    }

    public void withdraw(Map<Coin, Integer> coinsToWithdraw) {
        coinsToWithdraw.forEach((k, v) -> this.coins.put(k, (this.coins.get(k) - v)));
        calcAmount();
    }

    private void calcAmount() {
        this.amount = BigDecimal.valueOf(0);
        this.coins.forEach((k, v) -> this.amount = this.amount.add(
                v > 0 ? k.getNominal().multiply(BigDecimal.valueOf(v)) : BigDecimal.ZERO
        ));
    }
}
