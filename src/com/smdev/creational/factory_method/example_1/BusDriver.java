package com.smdev.creational.factory_method.example_1;

public class BusDriver implements VehicleDriver {
    @Override
    public void driveVehicle(Vehicle vehicle) {
        System.out.println("I'm a bus driver, driving a " + vehicle);
    }
}
