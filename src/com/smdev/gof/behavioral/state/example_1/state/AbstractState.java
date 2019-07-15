package com.smdev.gof.behavioral.state.example_1.state;

import com.smdev.gof.behavioral.state.example_1.OrderState;
import lombok.ToString;

@ToString
public abstract class AbstractState implements OrderState {

    private String name;

    public AbstractState(String name) {
        this.name = name;
    }
}
