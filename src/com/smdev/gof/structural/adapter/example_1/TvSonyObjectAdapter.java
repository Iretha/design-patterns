package com.smdev.gof.structural.adapter.example_1;

public class TvSonyObjectAdapter implements RemoteDevice {

    private TvSony adaptee;

    public TvSonyObjectAdapter(TvSony adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void turnOn() {
        this.adaptee.switchOn();
    }

    @Override
    public void turnOff() {
        this.adaptee.switchOff();
    }
}
