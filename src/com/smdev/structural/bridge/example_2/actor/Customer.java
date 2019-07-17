package com.smdev.structural.bridge.example_2.actor;

public class Customer implements Actor {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
