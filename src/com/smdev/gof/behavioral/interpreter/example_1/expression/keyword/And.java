package com.smdev.gof.behavioral.interpreter.example_1.expression.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.expression.AbstractExpression;
import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

import java.util.List;

public class And extends AbstractExpression {

    public And(Context context) {
        super(context);
    }

    public List<Dog> evaluate(List<Dog> data) {
        return data;
    }
}
