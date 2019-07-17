package com.smdev.creational.abstract_factory.example_1;

import com.smdev.creational.abstract_factory.example_1.android.AndroidUIFactory;
import com.smdev.creational.abstract_factory.example_1.swift.SwiftUIFactory;

public enum Platform {
    ANDROID,
    SWIFT;

    public UIFactory getFactory() {
        UIFactory factory = null;
        switch (this) {
            case ANDROID:
                factory = new AndroidUIFactory();
                break;
            case SWIFT:
                factory = new SwiftUIFactory();
                break;
            default:
                break;
        }
        return factory;
    }
}
