package com.smdev.gof.structural.bridge.example_1.vehicle;

import com.smdev.gof.structural.bridge.example_1.gear.Gear;

public class Bus extends Vehicle {

    public Bus(Gear gear) {
        super(gear);
    }
}
