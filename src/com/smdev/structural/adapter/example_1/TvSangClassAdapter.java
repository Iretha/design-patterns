package com.smdev.structural.adapter.example_1;

public class TvSangClassAdapter extends TvSang implements RemoteDevice {

    @Override
    public void turnOn() {
        play();
    }

    @Override
    public void turnOff() {
        shutDown();
    }
}
