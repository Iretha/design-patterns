package com.smdev.creational.singleton.example_3;

import lombok.ToString;

import java.io.Serializable;

@ToString
public enum SerializedEnumSingleton implements Serializable {
    INSTANCE;

    public void doSomething() {
        System.out.println(this + ": Doing something");
    }
}
