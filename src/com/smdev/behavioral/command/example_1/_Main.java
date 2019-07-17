package com.smdev.behavioral.command.example_1;

public class _Main {

    public static void main(String[] args) {
        RemoteControl tvRemoteControl = new RemoteControl(new Device("TV"));

        tvRemoteControl.pressButton("Play");
        tvRemoteControl.pressButton("Stop");
    }
}
