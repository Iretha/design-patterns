package com.smdev.gof.structural.bridge.example_2.actor;

public class Employee implements Actor {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
