package com.smdev.gof.behavioral.interpreter.example_1.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Dog implements Entry {

    private String name;
    private String breed;
    private String gender;
    private int age;

    public Dog(String name, String breed, String gender, int age) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }
}
