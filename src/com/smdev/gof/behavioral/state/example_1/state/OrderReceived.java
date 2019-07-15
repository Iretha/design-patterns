package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderContext;
import com.smdev.gof.behavioral.state.example_1.OrderState;

public class OrderConfirmed extends AbstractState {

    public OrderConfirmed() {
        super("Order Confirmed!");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new AwaitingAssembly();
    }
}
