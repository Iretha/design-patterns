package com.smdev.gof.behavioral.interpreter.example_1;

import java.util.List;

public interface Expression {

    void parse();

    List<Dog> evaluate() throws Exception;

    boolean isTerminal();

    List<Expression> getChildren();
}
