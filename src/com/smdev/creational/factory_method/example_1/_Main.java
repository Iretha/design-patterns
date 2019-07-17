package com.smdev.creational.factory_method.example_1;

public class _Main {

    public static void main(String[] args) {
        Vehicle vehicle1 = new Car();
        vehicle1.drive();

        Vehicle vehicle2 = new Bus();
        vehicle2.drive();
    }
}
