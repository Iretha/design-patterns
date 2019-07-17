package com.smdev.creational.abstract_factory.example_1.android;

import com.smdev.creational.abstract_factory.example_1.InputText;

public class AndroidInputText implements InputText {

    private String value;

    public AndroidInputText() {
        System.out.println("Android InputText created.");
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
