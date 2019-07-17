package com.smdev.structural.bridge.example_2.document;

import com.smdev.structural.bridge.example_2.actor.Actor;
import lombok.ToString;

@ToString
public class Document {

    private Actor createdBy;

    private Actor recipient;

    public Document(Actor createdBy) {
        this.createdBy = createdBy;

        System.out.println("This " + getClass().getSimpleName() + " is created by " + createdBy.getName());
    }

    public void sendTo(Actor recipient) {
        this.recipient = recipient;

        System.out.println("This " + getClass().getSimpleName() + " is sent to the " + recipient.getName());
    }
}
