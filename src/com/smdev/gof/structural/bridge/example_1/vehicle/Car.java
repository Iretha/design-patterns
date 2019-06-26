package com.smdev.gof.structural.bridge.example_1.vehicle;

import com.smdev.gof.structural.bridge.example_1.gear.Gear;

public class Car extends Vehicle {

    public Car(Gear gear) {
        super(gear);
    }
}
