package com.smdev.behavioral.strategy.example_1;

import lombok.ToString;

@ToString
public class SendStrategySms implements SendStrategy {

    private String fromPhoneNo;

    private String toPhoneNo;

    public SendStrategySms(String fromPhoneNo, String toPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
        this.toPhoneNo = toPhoneNo;
    }

    @Override
    public void send(Message message) {
        System.out.println("Sending SMS: " + this + "; message=" + message);
    }
}
