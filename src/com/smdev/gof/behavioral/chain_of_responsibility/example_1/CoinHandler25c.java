package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

/**
 * Handles only 25 Cent Coins
 */
public class CoinHandler25c extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 1.75 && coin.getWeight() == 5.67) {
            System.out.println("25 Cents " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}
