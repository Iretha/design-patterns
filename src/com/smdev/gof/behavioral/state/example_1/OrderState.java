package com.smdev.gof.behavioral.state.example_1;

public interface OrderState {

    OrderState next(OrderContext context);
}
