package com.smdev.task.task0002.parkinglot.payment;

public class PaymentStrategyByCard extends PaymentStrategy {

    private Card card;

    public PaymentStrategyByCard(Payment payment, Card card) {
        super(payment);
    }

    @Override
    public Recipe pay() {
        //TODO
        return new Recipe();
    }
}
