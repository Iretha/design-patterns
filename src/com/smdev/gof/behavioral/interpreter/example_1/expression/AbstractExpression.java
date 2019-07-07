package com.smdev.gof.behavioral.interpreter.example_1.expression;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import lombok.Getter;

public abstract class AbstractExpression implements Expression {

    @Getter
    private Context context;

    public AbstractExpression(Context context) {
        this.context = context;
    }
}
