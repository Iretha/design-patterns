package com.smdev.creational.factory_method.example_1;

public class CarDriver implements VehicleDriver {
    @Override
    public void driveVehicle(Vehicle vehicle) {
        System.out.println("I'm a car driver, driving a " + vehicle);
    }
}
