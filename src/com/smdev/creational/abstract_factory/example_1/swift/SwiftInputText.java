package com.smdev.creational.abstract_factory.example_1.swift;

import com.smdev.creational.abstract_factory.example_1.InputText;

public class SwiftInputText implements InputText {

    private String value;

    public SwiftInputText() {
        System.out.println("Swift InputText created.");
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String setValue(String value) {
        return this.value = value;
    }

    @Override
    public void submit() {
        System.out.println(this.value);
    }
}
