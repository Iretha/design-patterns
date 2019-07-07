package com.smdev.gof.behavioral.interpreter.example_1.expression;

import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

import java.util.List;

public interface Expression {

    List<Dog> evaluate(List<Dog> data) throws Exception;
}
