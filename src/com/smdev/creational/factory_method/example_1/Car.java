package com.smdev.creational.factory_method.example_1;

import lombok.ToString;

@ToString
public class Car extends Vehicle {

    @Override
    public VehicleDriver createDriverInstance() {
        return new CarDriver();
    }

}
