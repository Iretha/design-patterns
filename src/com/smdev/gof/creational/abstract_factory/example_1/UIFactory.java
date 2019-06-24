package com.smdev.gof.creational.abstract_factory.example_1;

public abstract class UIFactory {

    protected abstract Button createButton();

    protected abstract InputText createInput();
}
