package com.smdev.structural.bridge.example_1.gear;

public class AutomaticGear implements Gear {

    @Override
    public void change() {
        // do something automatically
    }

    @Override
    public String toString() {
        return "Automatic Transmission";
    }
}
