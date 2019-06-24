package com.smdev.gof.creational.factory_method.example_1;

public class CarDriver extends VehicleDriver {

    @Override
    public Vehicle getVehicle() {
        return new Car();
    }
}
