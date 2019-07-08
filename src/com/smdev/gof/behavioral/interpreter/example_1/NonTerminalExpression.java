package com.smdev.gof.behavioral.interpreter.example_1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Non-terminal expression = composite expression = has sub-expression
 */
public abstract class NonTerminalExpression extends TerminalExpression {

    @Getter
    private List<Expression> children = new ArrayList<>();

    public NonTerminalExpression(Context context) {
        super(context);
    }

    protected void readChildArgs() {
        List<Expression> children = getChildren();
        for (Expression ch : children) {
            ch.readArgs();
        }
    }

    protected List<Dog> uniteSubResults() throws Exception {
        List<Expression> children = getChildren();
        if (children.isEmpty()) {
            return getContext().getData();
        }

        Set<Dog> result = new HashSet<>();
        for (Expression ch : children) {
            result.addAll(ch.evaluate()); // keep only common elements
        }
        return new ArrayList<>(result);
    }

    protected List<Dog> intersectSubResults() throws Exception {
        Set<Dog> result = new HashSet<>(getContext().getData());
        List<Expression> children = getChildren();
        for (Expression ch : children) {
            result.retainAll(ch.evaluate()); // keep only common elements for all children
        }
        return new ArrayList<>(result);
    }
}
