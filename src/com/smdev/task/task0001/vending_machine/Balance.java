package com.smdev.task.task0001.vending_machine;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Balance {

    @Getter(AccessLevel.PACKAGE)
    private Map<CoinType, Integer> coins;

    @Getter
    private BigDecimal amount;

    public Balance() {
        init();
    }

    public Balance(Map<CoinType, Integer> initialCoins) {
        this();
        deposit(initialCoins);

        System.out.println("Initial Balance " + getAmount());
    }

    void init() {
        this.coins = new HashMap<>();
        this.amount = BigDecimal.valueOf(0);

        CoinType[] coinTypes = CoinType.values();
        for (CoinType coinType : coinTypes) {
            if (!CoinType.UNKNOWN.equals(coinType)) {
                this.coins.put(coinType, 0);
            }
        }
    }

    public void deposit(Map<CoinType, Integer> coins) {
        for (Map.Entry<CoinType, Integer> entry : coins.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void add(CoinType coin) {
        add(coin, 1);
    }

    private void add(CoinType coin, Integer count) {
        if (coin == null) {
            return;
        }

        this.coins.put(coin, this.coins.get(coin) + count);
        this.amount = this.amount.add(BigDecimal.valueOf(count).multiply(coin.getNominal()));
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

    public void withdraw(Map<CoinType, Integer> coinsToWithdraw) {
        coinsToWithdraw.forEach((k, v) -> this.coins.put(k, (this.coins.get(k) - v)));
        calcAmount();
    }

    public void withdrawChange(BigDecimal change) throws Exception {
        if (change == null || change.doubleValue() == 0) {
            System.out.println("Exact amount. No change.");
            return;
        }

        ChangeStrategy strategy = new ChangeStrategyBiggestFirst();
        Map<CoinType, Integer> amountInCoins = strategy.convertInCoins(getCoins(), change);
        withdraw(amountInCoins);

        System.out.println("Returning Change to Customer=" + change.doubleValue());

    }

    private void calcAmount() {
        this.amount = BigDecimal.valueOf(0);
        this.coins.forEach((k, v) -> this.amount = this.amount.add(
                v > 0 ? k.getNominal().multiply(BigDecimal.valueOf(v)) : BigDecimal.ZERO
        ));
    }
}
