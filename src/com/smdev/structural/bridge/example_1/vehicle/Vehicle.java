package com.smdev.structural.bridge.example_1.vehicle;

import com.smdev.structural.bridge.example_1.gear.Gear;

public class Vehicle {

    private Gear gear;

    public Vehicle(Gear gear) {
        this.gear = gear;

        if (this.gear != null) {
            System.out.println("New " + getClass().getSimpleName() + " with " + gear + " created!");
        } else {
            System.out.println("New " + getClass().getSimpleName() + " with no gear created!");
        }
    }

    public void changeGear() {
        if (this.gear != null) {
            this.gear.change();

            System.out.println("Changing gear - " + this.gear);
        } else {
            System.out.println("No gear to available!");
        }
    }
}
