package com.smdev.gof.structural.bridge.example_1.gear;

public class ManualGear implements Gear {

    @Override
    public void change() {
        // do something manual
    }

    @Override
    public String toString() {
        return "Manual Transmission";
    }
}
