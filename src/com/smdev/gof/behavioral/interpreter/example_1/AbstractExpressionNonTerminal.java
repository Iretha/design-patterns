package com.smdev.gof.behavioral.interpreter.example_1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Non-terminal expression = composite expression = has children
 */
public abstract class AbstractExpressionNonTerminal extends AbstractExpression {

    @Getter
    private List<Expression> children = new ArrayList<>();

    public AbstractExpressionNonTerminal(Context context) {
        super(context);
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    protected void parseChildren() {
        List<Expression> children = getChildren();
        for (Expression ch : children) {
            ch.parse();
        }
    }
}
