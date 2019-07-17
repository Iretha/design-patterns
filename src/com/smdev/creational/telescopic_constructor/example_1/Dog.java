package com.smdev.creational.telescopic_constructor.example_1;

import lombok.ToString;

import java.util.Date;

@ToString
public class Dog {
    private String name;
    private String breed;
    private int age;

    private Date registrationTime;

    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;

        this.registrationTime = new Date();
    }

    public Dog(String breed, int age) {
        this("Unknown name", breed, age);
    }

    public Dog(String name) {
        this(name, "Unknown breed", -1);
    }
}
