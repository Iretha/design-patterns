package com.smdev.gof.structural.bridge.example_2.document;

import com.smdev.gof.structural.bridge.example_2.actor.Actor;

public class Letter extends Document {

    public Letter(Actor createdBy) {
        super(createdBy);
    }
}
