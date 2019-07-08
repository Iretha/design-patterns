package com.smdev.gof.behavioral.interpreter.example_1;

import lombok.Getter;

public abstract class AbstractExpression implements Expression {

    @Getter
    private Context context;

    public AbstractExpression(Context context) {
        this.context = context;
    }
}
