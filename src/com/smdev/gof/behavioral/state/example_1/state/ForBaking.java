package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderContext;
import com.smdev.gof.behavioral.state.example_1.OrderState;

public class AwaitingBaking extends AbstractState {

    public AwaitingBaking() {
        super("For Baking");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new BakingInProgress();
    }
}
