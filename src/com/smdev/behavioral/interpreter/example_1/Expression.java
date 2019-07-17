package com.smdev.behavioral.interpreter.example_1;

import java.util.List;

public interface Expression {

    void readArgs();

    List<Dog> evaluate() throws Exception;
}
