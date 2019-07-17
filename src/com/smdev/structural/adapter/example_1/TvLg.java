package com.smdev.structural.adapter.example_1;

public class TvLg implements RemoteDevice {

    @Override
    public void turnOn() {
        System.out.println("TV LG is turned on!");
    }

    @Override
    public void turnOff() {
        System.out.println("TV LG is turned off!");
    }
}
