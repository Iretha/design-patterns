package com.smdev.creational.factory.example_1;

import java.util.ArrayList;
import java.util.List;

public class _Main {

    public static void main(String[] args) {
        List<Vehicle> vehiclesToAssemble = new ArrayList<>();
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.CAR, "with manual transmission"));
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.CAR, "with auto transmission"));
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.TRUCK, "all extras"));

        for (Vehicle vehicle : vehiclesToAssemble) {
            vehicle.assemble();
        }
    }
}
