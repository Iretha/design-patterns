package com.smdev.creational.abstract_factory.example_1.android;

import com.smdev.creational.abstract_factory.example_1.Button;

public class AndroidButton implements Button {

    public AndroidButton() {
        System.out.println("Android Button Created!");
    }

    @Override
    public void click() {
        System.out.println("Android Button Clicked!");
    }
}
