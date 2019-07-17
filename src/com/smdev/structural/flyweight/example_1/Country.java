package com.smdev.structural.flyweight.example_1;

public class Country {

    private String name;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString().concat("(" + this.name + ")");
    }
}
