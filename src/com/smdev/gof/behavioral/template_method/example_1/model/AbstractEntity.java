package com.smdev.gof.behavioral.template_method.example_1.model;

public abstract class AbstractEntity {

    private String name;

    public AbstractEntity() {
        System.out.println("New instance of " + getClass().getSimpleName() + " created!");
    }
}
