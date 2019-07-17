package com.smdev.creational.factory.example_1;

import lombok.ToString;

@ToString
public class Truck extends AbstractVehicle {

    public Truck(String modification) {
        super(modification);
    }

    @Override
    public Vehicle assemble() {
        System.out.println("A truck with " + getModification() + " is assembled!");
        return this;
    }
}
