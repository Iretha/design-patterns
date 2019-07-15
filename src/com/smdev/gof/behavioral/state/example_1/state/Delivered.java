package com.smdev.gof.behavioral.state.example_1;

public class Delivered implements OrderState {
    @Override
    public OrderState next(OrderContext context) {
        // the final state
        return null;
    }
}
