package com.smdev.gof.behavioral.state.example_1;

public class PizzaOrderContext {

    private String client;
    private String clientPhoneNo;
    private String deliveryAddress;

    private PizzaState pizzaState;

    public PizzaOrderContext(String client, String clientPhoneNo, String deliveryAddress) {
        this.client = client;
        this.clientPhoneNo = clientPhoneNo;
        this.deliveryAddress = deliveryAddress;
    }

    public void order() {
        this.pizzaState = new PizzaOrderConfirmed(this);
    }
}
