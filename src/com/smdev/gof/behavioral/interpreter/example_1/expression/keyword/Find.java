package com.smdev.gof.behavioral.interpreter.example_1.expression.keyword;


import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.expression.AbstractExpression;
import com.smdev.gof.behavioral.interpreter.example_1.model.Dog;

import java.util.List;

public class Find extends AbstractExpression {

    public Find(Context context) {
        super(context);
    }

    @Override
    public List<Dog> evaluate(List<Dog> data) {
        String arg = getContext().getArgs().poll();
        if (arg != null && getContext().getData().get(arg) != null) {
           return getContext().getData().get(arg);
        }
        return data;
    }
}
