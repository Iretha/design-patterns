package com.smdev.behavioral.state.example_1.state;

import com.smdev.behavioral.state.example_1.OrderContext;
import com.smdev.behavioral.state.example_1.OrderState;

public class AssemblyInProgress extends AbstractState {

    public AssemblyInProgress() {
        super("Assembly In Progress");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new ForBaking();
    }
}
