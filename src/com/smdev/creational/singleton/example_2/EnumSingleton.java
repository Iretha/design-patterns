package com.smdev.creational.singleton.example_2;

import lombok.ToString;

@ToString
public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println(this + ": Doing something");
    }
}
