package com.smdev.gof.behavioral.strategy.example_1;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
public class Message {

    @Getter
    private String text;

    @Getter
    private Date createdOn;

    public Message(String text) {
        this.text = text;
        this.createdOn = new Date();
    }
}
