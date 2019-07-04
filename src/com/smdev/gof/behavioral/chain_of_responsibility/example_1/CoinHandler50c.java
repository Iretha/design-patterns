package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

/**
 * Handles only 50 Cent Coins
 */
public class CoinHandler50c extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 2.15 && coin.getWeight() == 11.34) {
            System.out.println("50 Cents " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}
