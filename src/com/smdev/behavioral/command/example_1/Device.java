package com.smdev.behavioral.command.example_1;

import lombok.ToString;

@ToString
public class Device {

    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void play() {
        System.out.println(this + " is playing.");
    }

    public void stop() {
        System.out.println(this + " has stopped playing.");
    }
}
