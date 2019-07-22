package com.smdev.creational.singleton.example_3;

import java.io.Serializable;

public class SerializedClassicSingleton implements Serializable {

    /**
     * Volatile is used to indicate that a variable's value will be modified by different threads
     */
    private static volatile SerializedClassicSingleton instance = null;


    private SerializedClassicSingleton() {
    }

    public static SerializedClassicSingleton getInstance() {
        if (instance == null) {
            synchronized (SerializedClassicSingleton.class) {
                if (instance == null) {
                    instance = new SerializedClassicSingleton();
                }
            }
        }
        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
