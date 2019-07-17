package com.smdev.structural.adapter.example_1;

public class RemoteControl {

    private RemoteDevice currentDevice;

    public void connect(RemoteDevice device) {
        if (this.currentDevice == null) {
            this.currentDevice = device;

            System.out.println("Connected to " + this.currentDevice.getClass().getSimpleName());
        }else{
            System.out.println("Connected to another device. Please disconnect it first!");
        }
    }

    public void turnOn() {
        if (this.currentDevice != null) {
            this.currentDevice.turnOn();
        }
    }

    public void turnOff() {
        if (this.currentDevice != null) {
            this.currentDevice.turnOff();
        }
    }

    public void disconnect() {
        System.out.println("Disconnected from " + this.currentDevice.getClass().getSimpleName());

        this.currentDevice = null;
    }
}
