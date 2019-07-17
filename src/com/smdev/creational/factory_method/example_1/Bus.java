package com.smdev.creational.factory_method.example_1;

import lombok.ToString;

@ToString
public class Bus extends Vehicle {

    @Override
    public VehicleDriver createDriverInstance() {
        return new BusDriver();
    }

}
