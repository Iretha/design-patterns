package com.smdev.creational.factory.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class AbstractVehicle implements Vehicle {

    @Getter
    private String modification;

    public AbstractVehicle(String modification) {
        this.modification = modification;
    }
}
