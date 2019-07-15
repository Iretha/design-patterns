package com.smdev.gof.behavioral.state.example_1;

public class BakingInProgress implements OrderState {
    @Override
    public OrderState next(OrderContext context) {
        return new AwaitingDelivery();
    }
}
