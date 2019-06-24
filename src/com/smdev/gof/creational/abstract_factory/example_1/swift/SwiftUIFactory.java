package com.smdev.gof.creational.abstract_factory.example_1.swift;

import com.smdev.gof.creational.abstract_factory.example_1.UIFactory;
import com.smdev.gof.creational.abstract_factory.example_1.Button;
import com.smdev.gof.creational.abstract_factory.example_1.InputText;

public class SwiftUIFactory extends UIFactory {

    public static final String TYPE = "Swift";

    @Override
    protected Button createButton() {
        return new SwiftButton();
    }

    @Override
    protected InputText createInput() {
        return new SwiftInputText();
    }
}
