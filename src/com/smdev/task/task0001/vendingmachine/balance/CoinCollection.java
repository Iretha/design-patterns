package com.smdev.task.task0001.vendingmachine.balance;

import com.smdev.task.task0001.vendingmachine.ItemCollection;
import lombok.Getter;

import java.util.Map;

/**
 * Collection of coins
 */
public class CoinCollection extends ItemCollection<Coin> {

    @Getter
    private int totalAmount;

    public CoinCollection() {
        super();
    }

    @Override
    public void add(Coin coin, int quantity) {
        super.add(coin, quantity);
        this.totalAmount += quantity * coin.getDenomination();
    }

    @Override
    public boolean release(Coin coin, int quantity) {
        if (super.release(coin, quantity)) {
            this.totalAmount -= quantity * coin.getDenomination();
            return true;
        }
        return false;
    }

    public void releaseAll() {
        getItems().forEach(this::release);
        this.totalAmount = 0;
    }

    public void acquireCollection(CoinCollection collection) {
        Map<Coin, Integer> orderCoins = collection.getItems();
        orderCoins.forEach(this::add);
        collection.clearQuantities();
    }

    public void releaseCollection(CoinCollection collection) {
        Map<Coin, Integer> refundCoins = collection.getItems();
        for (Map.Entry<Coin, Integer> entry : refundCoins.entrySet()) {
            Coin k = entry.getKey();
            Integer v = entry.getValue();
            release(k, v);
        }
    }

    public boolean isEmpty(){
        return this.totalAmount == 0;
    }
}
