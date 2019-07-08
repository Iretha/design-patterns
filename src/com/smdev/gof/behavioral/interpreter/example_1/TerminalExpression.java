package com.smdev.gof.behavioral.interpreter.example_1;

import lombok.Getter;

/**
 * terminal expression = simple expression = no children
 */
public abstract class TerminalExpression implements Expression {

    @Getter
    private Context context;

    public TerminalExpression(Context context) {
        this.context = context;
    }
}
