package com.smdev.gof.behavioral.state.example_1;

import com.smdev.gof.behavioral.state.example_1.state.OrderReceived;
import lombok.Getter;
import lombok.ToString;

@ToString
public class OrderContext {

    @Getter
    private String client;

    @Getter
    private String deliveryAddress;

    private OrderState state;

    public OrderContext(String client, String deliveryAddress) {
        this.client = client;
        this.deliveryAddress = deliveryAddress;

        this.state = new OrderReceived();

        System.out.println("Order Received: " + this);
    }

    public void next() {
        OrderState nextState = this.state.next(this);
        if (nextState != null) {
            this.state = nextState;
            System.out.println("Next: " + this);
        } else {
            System.out.println("Order completed!");
        }
    }
}
