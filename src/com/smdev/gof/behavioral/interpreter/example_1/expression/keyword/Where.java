package com.smdev.gof.behavioral.interpreter.example_1.expression.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.expression.AbstractExpression;
import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

import java.util.List;

public class Where extends AbstractExpression {

    public Where(Context context) {
        super(context);
    }

    @Override
    public List<Dog> evaluate(List<Dog> data) {
        return data;
    }
}
