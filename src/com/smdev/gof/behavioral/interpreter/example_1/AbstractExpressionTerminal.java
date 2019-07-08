package com.smdev.gof.behavioral.interpreter.example_1;

import java.util.List;

/**
 * terminal expression = simple expression = no children
 */
public abstract class AbstractExpressionTerminal extends AbstractExpression {

    public AbstractExpressionTerminal(Context context) {
        super(context);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public List<Expression> getChildren() {
        return null;
    }
}
