package com.smdev.behavioral.null_object.example_1;

import lombok.ToString;

@ToString
public abstract class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
