package com.smdev.task.task0002.parkinglot.payment;

import lombok.Getter;

import javax.annotation.processing.Generated;

public abstract class PaymentStrategy {

    @Getter
    private Payment payment;

    public PaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public abstract Recipe pay();
}
