package com.smdev.gof.structural.bridge.example_1;

import com.smdev.gof.structural.bridge.example_1.gear.AutomaticGear;
import com.smdev.gof.structural.bridge.example_1.gear.ManualGear;
import com.smdev.gof.structural.bridge.example_1.vehicle.Bus;
import com.smdev.gof.structural.bridge.example_1.vehicle.Car;
import com.smdev.gof.structural.bridge.example_1.vehicle.Vehicle;

public class _Main {

    public static void main(String[] args) {
        Vehicle car1 = new Car(new ManualGear());
        car1.changeGear();

        Vehicle car2 = new Car(new AutomaticGear());
        car2.changeGear();

        Vehicle bus1 = new Bus(new AutomaticGear());
        bus1.changeGear();
    }
}
