package com.smdev.gof.behavioral.strategy.example_1;

import lombok.ToString;

@ToString
public class SendStrategyEmail implements SendStrategy {

    private String from;

    private String to;

    public SendStrategyEmail(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending Email: " + this + "; message=" + message);
    }
}
