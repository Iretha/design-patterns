package com.smdev.creational.abstract_factory.example_1.swift;

import com.smdev.creational.abstract_factory.example_1.Button;
import com.smdev.creational.abstract_factory.example_1.InputText;
import com.smdev.creational.abstract_factory.example_1.UIFactory;

public class SwiftUIFactory extends UIFactory {

    @Override
    protected Button createButton() {
        return new SwiftButton();
    }

    @Override
    protected InputText createInput() {
        return new SwiftInputText();
    }
}
