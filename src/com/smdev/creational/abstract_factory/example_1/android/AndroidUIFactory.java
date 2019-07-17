package com.smdev.creational.abstract_factory.example_1.android;

import com.smdev.creational.abstract_factory.example_1.Button;
import com.smdev.creational.abstract_factory.example_1.InputText;
import com.smdev.creational.abstract_factory.example_1.UIFactory;

public class AndroidUIFactory extends UIFactory {

    @Override
    protected Button createButton() {
        return new AndroidButton();
    }

    @Override
    protected InputText createInput() {
        return new AndroidInputText();
    }
}
