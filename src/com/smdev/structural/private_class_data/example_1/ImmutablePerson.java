package com.smdev.structural.private_class_data.example_1;

import java.util.List;

public class ImmutablePerson {

    private final PersonData data;

    public ImmutablePerson(String name, int age, List<String> addresses) {
        this.data = new PersonData(name, age, addresses);
    }

    public void doSomething(){
        System.out.println(this.data);
    }
}
