package com.smdev.gof.behavioral.interpreter.example_1;

import lombok.Getter;

import java.util.*;

public class Context {

    @Getter
    private Deque<Expression> keywords = new LinkedList<>();

    @Getter
    private Deque<String> args = new LinkedList<>();

    @Getter
    private Map<String, List<Dog>> database;

    @Getter
    private List<Dog> data = new ArrayList<>();

    public Context(Map<String, List<Dog>> database, String expression) throws Exception {
        this.database = database;

        interpret(expression);
    }

    private void interpret(String strExpression) throws Exception {
        String[] tokens = strExpression.split("\\s+"); // split by blanks & spaces
        Expression expression;

        List<Expression> terminalExpressions = new ArrayList<>();
        for (String token : tokens) {
            expression = ExpressionFactory.createInstance(token, this);
            if (expression != null) {

                if (expression.isTerminal()) {
                    terminalExpressions.add(expression);
                } else {
                    getKeywords().add(expression);
                }

                if (terminalExpressions.size() == 2) {
                    getKeywords().peekLast().getChildren().addAll(terminalExpressions);
                    terminalExpressions.clear();
                }
            } else {
                getArgs().add(token);
            }
        }

        if (terminalExpressions.size() > 0) {
            getKeywords().peekLast().getChildren().addAll(terminalExpressions);
            terminalExpressions.clear();
        }
    }

    public List<Dog> evaluate() throws Exception {
        List<Dog> result = new ArrayList<>();

        Expression expression;
        while (!this.keywords.isEmpty()) {
            expression = getKeywords().pollFirst();
            expression.parse();
            result = expression.evaluate();
        }
        return result;
    }
}
