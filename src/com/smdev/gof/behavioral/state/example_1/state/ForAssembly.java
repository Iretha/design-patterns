package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderContext;
import com.smdev.gof.behavioral.state.example_1.OrderState;

public class AwaitingAssembly extends AbstractState {

    public AwaitingAssembly() {
        super("For Assembly");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new AssemblyInProgress();
    }
}
