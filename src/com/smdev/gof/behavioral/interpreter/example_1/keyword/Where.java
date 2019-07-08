package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.AbstractExpressionNonTerminal;
import com.smdev.gof.behavioral.interpreter.example_1.Expression;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Where extends AbstractExpressionNonTerminal {

    public Where(Context context) {
        super(context);
    }

    @Override
    public void parse() {
        parseChildren();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        List<Expression> children = getChildren();
        if(children.isEmpty()){
            return getContext().getData();
        }

        Set<Dog> result = new HashSet<>();
        for (Expression ch : children) {
            result.addAll(ch.evaluate()); // keep only common elements
        }
        return new ArrayList<>(result);
    }
}
