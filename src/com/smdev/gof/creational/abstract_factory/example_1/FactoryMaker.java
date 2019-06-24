package com.smdev.gof.creational.abstract_factory.example_1;

import com.smdev.gof.creational.abstract_factory.example_1.android.AndroidUIFactory;
import com.smdev.gof.creational.abstract_factory.example_1.swift.SwiftUIFactory;

public class FactoryMaker {

    public static UIFactory getFactory(String choice) {
        UIFactory factory = null;
        if (choice.equals(AndroidUIFactory.TYPE)) {
            factory = new AndroidUIFactory();
        } else if (choice.equals(SwiftUIFactory.TYPE)) {
            factory = new SwiftUIFactory();
        }
        return factory;
    }
}
