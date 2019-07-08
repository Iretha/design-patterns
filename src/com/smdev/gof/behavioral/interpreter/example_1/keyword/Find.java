package com.smdev.gof.behavioral.interpreter.example_1.keyword;


import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.AbstractExpressionNonTerminal;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.util.ArrayList;
import java.util.List;

public class Find extends AbstractExpressionNonTerminal {

    private String arg;

    public Find(Context context) {
        super(context);
    }

    @Override
    public void parse() {
        this.arg = getContext().getArgs().pollFirst();
    }

    @Override
    public List<Dog> evaluate(List<Dog> unfiltered) {
        List<Dog> list = getContext().getData().get(this.arg);
        return list != null ? list : new ArrayList<>();
    }
}
