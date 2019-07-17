package com.smdev.structural.composite.example_1;

import lombok.Getter;
import lombok.Setter;

public class File implements Entry {

    @Getter
    @Setter
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void ls(boolean recursive) {
        System.out.println(getName());
    }

}
