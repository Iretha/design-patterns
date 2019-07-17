package com.smdev.creational.abstract_factory.example_1.swift;

import com.smdev.creational.abstract_factory.example_1.Button;

public class SwiftButton implements Button {

    public SwiftButton() {
        System.out.println("Swift Button Created!");
    }

    @Override
    public void click() {
        System.out.println("Swift Button Clicked!");
    }
}
