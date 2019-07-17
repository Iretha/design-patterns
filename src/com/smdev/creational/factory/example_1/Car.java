package com.smdev.creational.factory.example_1;

import lombok.ToString;

@ToString
public class Car extends AbstractVehicle {

    public Car(String modification) {
        super(modification);
    }

    @Override
    public Vehicle assemble() {
        System.out.println("A car with " + getModification() + " is assembled!");
        return this;
    }
}
