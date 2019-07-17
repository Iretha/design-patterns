package com.smdev.behavioral.strategy.example_1;

import lombok.ToString;

@ToString
public class SendStrategyFacebook implements SendStrategy {

    private String from;

    private String to;

    public SendStrategyFacebook(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending FB Message: " + this + "; message=" + message);
    }
}
