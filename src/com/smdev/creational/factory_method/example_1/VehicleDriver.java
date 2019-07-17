package com.smdev.creational.factory_method.example_1;

public abstract class VehicleDriver {

    public abstract Vehicle getVehicle();

    public void driveVehicle() {
        System.out.println("I'm a " + getClass().getSimpleName() + " and I'm ");

        Vehicle vehicle = getVehicle();
        vehicle.drive();
    }
}
