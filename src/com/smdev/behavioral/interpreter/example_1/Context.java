package com.smdev.behavioral.interpreter.example_1;

import lombok.Getter;

import java.util.*;

public class Context {

    @Getter
    private Deque<NonTerminalExpression> composites = new LinkedList<>();

    @Getter
    private Deque<String> args = new LinkedList<>();

    @Getter
    private Map<String, List<Dog>> database;

    @Getter
    private List<Dog> data = new ArrayList<>();

    public Context(Map<String, List<Dog>> database, String searchExpression) throws Exception {
        this.database = database;

        interpret(searchExpression);
    }

    public List<Dog> evaluate() throws Exception {
        List<Dog> result = new ArrayList<>();

        Expression expression;
        while (!this.composites.isEmpty()) {
            expression = getComposites().pollFirst();
            expression.readArgs();
            result = expression.evaluate();
        }
        return result;
    }

    /**
     * Split search expression by blanks & spaces. Try to parse each token as an expression.
     * If it's not recognized as an expression, than it is an argument.
     * If two simple expressions found, then we add them as children to the last added composite expression.
     * At the end, if just one simple expression has left, then we add is as a child to the last added composite expression as well.
     *
     * @param searchExpression
     * @throws Exception
     */
    private void interpret(String searchExpression) throws Exception {
        String[] tokens = searchExpression.split("\\s+"); // split by blanks & spaces

        List<Expression> children = new ArrayList<>();
        Expression expression;
        for (String token : tokens) {
            expression = ExpressionFactory.createInstance(token, this);

            if (expression == null) {
                getArgs().add(token); // it's an argument
            } else {
                if (expression instanceof NonTerminalExpression) {
                    getComposites().add(NonTerminalExpression.class.cast(expression)); // it's composite expression (may have children)
                } else {
                    children.add(expression);
                }

                if (children.size() == 2) {
                    addChildren(getComposites().peekLast(), children);
                }
            }
        }
        addChildren(getComposites().peekLast(), children);
    }

    private void addChildren(NonTerminalExpression parent, List<Expression> children) {
        parent.getChildren().addAll(children);
        children.clear();
    }
}
