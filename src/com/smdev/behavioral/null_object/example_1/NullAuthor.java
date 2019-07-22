package com.smdev.behavioral.null_object.example_1;

public class NullAuthor extends Author {

    private static NullAuthor instance;

    private NullAuthor() {
        super("Unknown author");
    }

    public static Author get() {
        if (instance == null) {
            instance = new NullAuthor();
        }
        return instance;
    }
}
