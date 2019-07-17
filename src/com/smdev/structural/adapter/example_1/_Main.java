package com.smdev.structural.adapter.example_1;

public class _Main {
    public static void main(String[] args) {
        testRemoteControl(new TvLg());  // doesn't need to be adapted, because LG supports our remote control
        testRemoteControl(new TvSangClassAdapter()); // adapted to support our remote control using class adapter
        testRemoteControl(new TvSonyObjectAdapter(new TvSony())); // adapted to support our remote control using object adapter
    }

    private static void testRemoteControl(RemoteDevice device) {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.connect(device);
        remoteControl.turnOn();
        remoteControl.turnOff();
        remoteControl.disconnect();
        System.out.println("");
    }
}
