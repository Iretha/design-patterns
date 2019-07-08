package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.NonTerminalExpression;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.util.List;

public class And extends NonTerminalExpression {

    public And(Context context) {
        super(context);
    }

    @Override
    public void readArgs() {
        readChildArgs();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        return intersectSubResults();
    }
}
