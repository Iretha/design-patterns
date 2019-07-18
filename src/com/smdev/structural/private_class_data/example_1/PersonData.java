package com.smdev.structural.private_class_data.example_1;

import lombok.ToString;
import java.util.Collections;
import java.util.List;

@ToString
public class PersonData {

    private String name;

    private int age;

    private List<String> addresses;

    public PersonData(String name, int age, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.addresses = addresses;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public List<String> getAddresses() {
        return Collections.unmodifiableList(this.addresses);
    }
}
