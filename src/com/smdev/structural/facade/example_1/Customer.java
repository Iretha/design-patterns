package com.smdev.structural.facade.example_1;

import lombok.Getter;

public class Customer {

    @Getter
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
