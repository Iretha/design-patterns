package com.smdev.creational.telescoping_constructor.example_1;

public class _Main {

    public static void main(String[] args) {
        Dog dog1 = new Dog("Kevin", "German Shepherd", 10);
        System.out.println(dog1);

        Dog dog2 = new Dog("Pug", 2);
        System.out.println(dog2);

        Dog dog3 = new Dog("Charlie");
        System.out.println(dog3);
    }
}
