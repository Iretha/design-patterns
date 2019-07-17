package com.smdev.behavioral.state.example_1.state;

import com.smdev.behavioral.state.example_1.OrderContext;
import com.smdev.behavioral.state.example_1.OrderState;

public class OrderReceived extends AbstractState {

    public OrderReceived() {
        super("Order Confirmed!");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new ForAssembly();
    }
}
