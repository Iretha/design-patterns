package com.smdev.behavioral.state.example_1.state;

import com.smdev.behavioral.state.example_1.OrderContext;
import com.smdev.behavioral.state.example_1.OrderState;

public class Delivered extends AbstractState {

    public Delivered() {
        super("Delivered To Address");
    }

    @Override
    public OrderState next(OrderContext context) {
        System.out.println("Delivered to " + context.getClient() + ": address=" + context.getDeliveryAddress());
        // the final state
        return null;
    }
}
