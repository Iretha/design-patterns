package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

public interface CoinHandler {

    CoinHandler setNextHandler(CoinHandler nextHandler);

    boolean put(Coin coin);
}
