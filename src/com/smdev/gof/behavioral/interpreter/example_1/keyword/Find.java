package com.smdev.gof.behavioral.interpreter.example_1.keyword;


import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;
import com.smdev.gof.behavioral.interpreter.example_1.NonTerminalExpression;

import java.util.List;

public class Find extends NonTerminalExpression {

    private String arg;

    public Find(Context context) {
        super(context);
    }

    @Override
    public void readArgs() {
        this.arg = getContext().getArgs().pollFirst();

        readChildArgs();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        List<Dog> list = getContext().getDatabase().get(this.arg);
        if (list != null) {
            getContext().getData().addAll(list);
        }
        return uniteSubResults();
    }
}
