package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.AbstractExpressionNonTerminal;
import com.smdev.gof.behavioral.interpreter.example_1.Expression;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class And extends AbstractExpressionNonTerminal {

    public And(Context context) {
        super(context);
    }

    @Override
    public void parse() {
        parseChildren();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        Set<Dog> result = new HashSet<>(getContext().getData());

        List<Expression> children = getChildren();
        for (Expression ch : children) {
            result.retainAll(ch.evaluate()); // keep only common elements for all children
        }
        return new ArrayList<>(result);
    }
}
