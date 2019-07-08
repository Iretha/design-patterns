package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;
import com.smdev.gof.behavioral.interpreter.example_1.NonTerminalExpression;

import java.util.List;

public class Or extends NonTerminalExpression {

    public Or(Context context) {
        super(context);
    }

    @Override
    public void readArgs() {
        readChildArgs();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        return uniteSubResults();
    }
}
