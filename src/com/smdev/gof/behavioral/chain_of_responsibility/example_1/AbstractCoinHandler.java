package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

import lombok.Getter;

public abstract class AbstractCoinHandler implements CoinHandler {

    @Getter
    private CoinHandler nextHandler;

    @Override
    public CoinHandler setNextHandler(CoinHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

}
