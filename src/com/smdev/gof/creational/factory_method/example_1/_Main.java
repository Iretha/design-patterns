package com.smdev.gof.creational.factory_method.example_1;

public class _Main {

    public static void main(String[] args) {
        drive(new BusDriver());
        drive(new CarDriver());
    }

    private static void drive(VehicleDriver driver){
        driver.driveVehicle();
    }
}
