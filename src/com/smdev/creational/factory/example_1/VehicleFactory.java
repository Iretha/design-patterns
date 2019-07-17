package com.smdev.creational.factory.example_1;

public final class VehicleFactory {
    private VehicleFactory() {
    }

    public static Vehicle getInstance(VehicleType type, String modification) {
        Vehicle vehicle = null;
        switch (type) {
            case CAR:
                vehicle = new Car(modification);
                break;
            case TRUCK:
                vehicle = new Truck(modification);
                break;
            default:
                break;
        }

        System.out.println("Vehicle created: " + vehicle);

        return vehicle;
    }
}
