package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderContext;
import com.smdev.gof.behavioral.state.example_1.OrderState;

public class ForDelivery extends AbstractState {

    public ForDelivery() {
        super("For Delivery");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new DeliveryInProgress();
    }
}
