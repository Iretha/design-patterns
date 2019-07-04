package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

/**
 * Handles only 1 Dollar Coins
 */
public class CoinHandler1d extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 2.58 && coin.getWeight() == 22.68) {
            System.out.println("1 Dollar " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}
