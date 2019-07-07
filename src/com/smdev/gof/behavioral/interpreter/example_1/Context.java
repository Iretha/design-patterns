package com.smdev.gof.behavioral.interpreter.example_1;


import com.smdev.gof.behavioral.interpreter.example_1.expression.Expression;
import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;
import lombok.Getter;

import java.util.*;

public class Context {

    @Getter
    private Queue<Expression> keywords = new LinkedList<>();

    @Getter
    private Queue<String> args = new LinkedList<>();

    @Getter
    private Map<String, List<Dog>> data;

    public Context(Map<String, List<Dog>> data) {
        this.data = data;
    }

    public List<Dog> evaluate() throws Exception {
        Expression expression;
        List<Dog> result = new ArrayList<>();
        while (!this.keywords.isEmpty()) {
            expression = this.keywords.poll();
            result = expression.evaluate(result);
        }
        return result;
    }
}
