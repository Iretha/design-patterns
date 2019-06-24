package com.smdev.gof.creational.factory_method.example_1;

public class BusDriver extends VehicleDriver {

    @Override
    public Vehicle getVehicle() {
        return new Bus();
    }
}
