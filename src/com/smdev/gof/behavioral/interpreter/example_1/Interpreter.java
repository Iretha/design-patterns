package com.smdev.gof.behavioral.interpreter.example_1;

import com.smdev.gof.behavioral.interpreter.example_1.expression.ExpressionType;

public final class Interpreter {

    private Interpreter() {
    }

    public static Context interpret(String expression, Context context) throws Exception {
        String[] tokens = expression.split("\\s+"); // split by blanks & spaces
        ExpressionType exprType;
        for (String token : tokens) {
            exprType = ExpressionType.getExpressionType(token);
            if (exprType != null) {
                context.getKeywords().add(exprType.createInstance(context));
            } else {
                context.getArgs().add(token);
            }
        }
        return context;
    }
}
