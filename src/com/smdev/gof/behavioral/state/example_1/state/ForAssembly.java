package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderContext;
import com.smdev.gof.behavioral.state.example_1.OrderState;

public class ForAssembly extends AbstractState {

    public ForAssembly() {
        super("For Assembly");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new AssemblyInProgress();
    }
}
