package com.smdev.gof.creational.abstract_factory.example_1.android;

import com.smdev.gof.creational.abstract_factory.example_1.UIFactory;
import com.smdev.gof.creational.abstract_factory.example_1.Button;
import com.smdev.gof.creational.abstract_factory.example_1.InputText;

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
