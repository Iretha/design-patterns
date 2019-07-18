package com.smdev.structural.private_class_data.example_1;

import java.util.Arrays;

public class _Main {

    public static void main(String[] args) {
        ImmutablePerson person1 = new ImmutablePerson("Jon Snow", 35, Arrays.asList("London", "Los Angeles"));
        person1.doSomething();
    }
}
