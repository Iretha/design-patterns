---
layout: default
title: Interpreter
parent: Behavioral Design Patterns
nav_order: 3020
---

# The Interpreter Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/interpreter) 

## What problems does it solve? Why to use it?
The interpreter pattern is a design pattern that specifies how to evaluate sentences in some custom language. 
The basic idea is to have a class for each symbol. The symbols are separated by another symbol i.e. "one plus two multiplied-by three", separated by spaces.
The syntax tree of a sentence in the language can be represented via the composite pattern.

Glossary:
- Grammar - syntax or set of rules of the language
- Expression - the expression you want to interpret
- Context - the common data, shared between expressions
- Non-terminal expression - may contain other non-terminal expression, you can recursively evaluate them
- Terminal expression - the last expression that will return the result
- Every symbol extends expression - you can separate the symbols with space or another delimiter (one plus two multiplied-by three)
- Infix - when the operators are between the left and the right expression: 5 + 1
- Postfix - the operator follows the left and the right expression: 5 1 +
- Prefix - the operator precedes the left and the right expression + 5 1

## When to use it?
- To build a simple grammar as an abstract syntax tree-like structure and run the evaluation
- To produce different outputs (like file formats etc)
- To create simple language processors
- To create simple domain-specific search engines

## Pros:
- You can define implementation for a language with simple grammar (syntax or rules)

## Cons:
- Requires a lot of validations and error checking
- Not good for complex grammar, because it gets very hard to maintain

## Examples from Java API
JVM have both compiler and interpreter. Because the compiler compiles the code and generates byte code. 
After that the interpreter converts byte code to machine understandable code. 

Recognizable by behavioral methods returning a structurally different instance/type of the given 
instance/type; note that parsing/formatting is not part of the pattern, determining the pattern and how to apply it is
```
java.util.Pattern
java.text.Normalizer
All subclasses of java.text.Format
All subclasses of javax.el.ELResolver
```
## Examples
### Example 1 - How to implement it?
We are going to implement a domain-specific search engine for our sample application. 
Our sample application will keep track of all dogs at a shelter. When someone want to adopt a dog, he can search in our database.

Here are some example searches we are going to implement:
```
- find dogs
- find dogs where gender eq male
- find dogs where breed eq german_shepherd and gender eq female
- find dogs where breed eq german_shepherd or breed eq bulldog and gender eq female

```        
1). We need to create the domain specific object - Dog.java 
```java
@ToString
@Getter
public class Dog {

    private String name;
    private String breed;
    private String gender;
    private int age;

    public Dog(String name, String breed, String gender, int age) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }
}
```
2). We need the expression interface
```java
public interface Expression {

    void readArgs();

    List<Dog> evaluate() throws Exception;
}
```
3). We need to define the two abstract types of the expressions - terminal (simple) and non-terminal (or composite).
Each expression will have the context as a member, so that it can access the args and data, in order to evaluate ut's value.

3.1). Terminal Expressions - these are the expressions that evaluate their values directly, they do not depend on other sub-expressions
```java
/**
 * terminal expression = simple expression = no children
 */
public abstract class TerminalExpression implements Expression {

    @Getter
    private Context context;

    public TerminalExpression(Context context) {
        this.context = context;
    }
}
```
3.2). Non-Terminal Expressions have children (that might be terminal or non-terminal) and in order to evaluate their own value, 
they need to evaluate their children values first.

!!! Note that we use the Composite Design Pattern here, in order to implement a non-terminal expression . 
```java
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
```
4). We need a Context class - the context will "know" how to parse string searches / inquiries (expressions), related to dogs.
Also the context has access to the dogs database and keeps the shared data between the expressions.
```java
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
```
5). Let's create the concrete expressions we could parse
5.1). Find
```java
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
```
5.2). Where (we can omit it in the expression, but we want still support this expression as well)
```java
public class Where extends NonTerminalExpression {

    public Where(Context context) {
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
```
5.3). And
```java
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
```
5.4). Or
```java
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
```
5.5). Eq aka Equals (currently the only terminal expression)
```java
public class Eq extends TerminalExpression {

    private String fieldName;
    private String searchedValue;

    public Eq(Context context) {
        super(context);
    }

    @Override
    public void readArgs() {
        this.fieldName = getContext().getArgs().pollFirst();
        this.searchedValue = getContext().getArgs().pollFirst();
    }

    @Override
    public List<Dog> evaluate() throws Exception {
        if (this.fieldName == null) {
            return getContext().getData();
        }
        List<Dog> filtered = new ArrayList<>();
        Field field = Dog.class.getDeclaredField(this.fieldName);
        field.setAccessible(true);
        for (Dog e : getContext().getData()) {
            Object value = field.get(e);
            if (this.searchedValue == null && value == null) {
                filtered.add(e);
            } else if (this.searchedValue != null && value != null && String.valueOf(value).equalsIgnoreCase(this.searchedValue)) {
                filtered.add(e);
            }
        }
        return filtered;
    }
}
```
6). We want an ExpressionFactory, in order to instantiate the right concrete classes depending on a keyword.
We will implement simple Factory Design Pattern using enum. :)
```java
public enum ExpressionFactory {

    FIND("find", Find.class),
    WHERE("where", Where.class),
    AND("and", And.class),
    OR("or", Or.class),
    EQ("eq", Eq.class);

    private String keyword;
    private Class<? extends Expression> clz;

    ExpressionFactory(String keyword, Class<? extends Expression> clz) {
        this.keyword = keyword;
        this.clz = clz;
    }

    public static Expression createInstance(String keyword, Context context) throws Exception {
        ExpressionFactory type = getExpressionType(keyword);
        if (type == null) {
            return null;
        }

        Constructor<? extends Expression> constructor = type.getClz().getConstructor(context.getClass());
        if (constructor != null) {
            return constructor.newInstance(context);
        }
        return null;
    }

    private static ExpressionFactory getExpressionType(String keyword) {
        if (keyword == null) {
            return null;
        }
        ExpressionFactory[] values = ExpressionFactory.values();
        for (ExpressionFactory t : values) {
            if (t.getKeyword().toLowerCase().equals(keyword.toLowerCase())) {
                return t;
            }
        }
        return null;
    }

    private String getKeyword() {
        return this.keyword;
    }

    private Class<? extends Expression> getClz() {
        return this.clz;
    }
}
```
7). Now our Demo class
```java
public class _Main {

    public static void main(String[] args) {
        Map<String, List<Dog>> data = new HashMap<>();
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Ruby", "german_shepherd", "female", 5));
        dogs.add(new Dog("Bobby", "german_shepherd", "male", 3));
        dogs.add(new Dog("Rusty", "german_shepherd", "male", 1));
        dogs.add(new Dog("Sara", "german_shepherd", "female", 2));
        dogs.add(new Dog("Diva", "german_shepherd", "female", 7));
        dogs.add(new Dog("Lila", "bulldog", "female", 8));
        dogs.add(new Dog("Lilo", "bulldog", "male", 12));
        dogs.add(new Dog("Viva", "pug", "female", 8));

        data.put("dogs", dogs);

        try {
            evaluate(data, "find dogs");
            evaluate(data, "find dogs where gender eq male");
            evaluate(data, "find dogs where breed eq german_shepherd and gender eq female");
            evaluate(data, "find dogs where breed eq german_shepherd or breed eq bulldog and gender eq female");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void evaluate(Map<String, List<Dog>> data, String expression) throws Exception {
        System.out.println("------ Expression to evaluate: " + expression);
        Context context = new Context(data, expression);
        List<Dog> result = context.evaluate();
        System.out.println(result);
        System.out.println();
    }
}
```
Output:
```
------ Expression to evaluate: find dogs gender eq male
[Dog(name=Bobby, breed=german_shepherd, gender=male, age=3), Dog(name=Rusty, breed=german_shepherd, gender=male, age=1), Dog(name=Lilo, breed=bulldog, gender=male, age=12)]

------ Expression to evaluate: find dogs where gender eq male
[Dog(name=Bobby, breed=german_shepherd, gender=male, age=3), Dog(name=Rusty, breed=german_shepherd, gender=male, age=1), Dog(name=Lilo, breed=bulldog, gender=male, age=12)]

------ Expression to evaluate: find dogs where breed eq german_shepherd and gender eq female
[Dog(name=Sara, breed=german_shepherd, gender=female, age=2), Dog(name=Ruby, breed=german_shepherd, gender=female, age=5), Dog(name=Diva, breed=german_shepherd, gender=female, age=7)]

------ Expression to evaluate: find dogs where breed eq german_shepherd or breed eq bulldog and gender eq female
[Dog(name=Sara, breed=german_shepherd, gender=female, age=2), Dog(name=Viva, breed=pug, gender=female, age=8), Dog(name=Ruby, breed=german_shepherd, gender=female, age=5), Dog(name=Lila, breed=bulldog, gender=female, age=8), Dog(name=Diva, breed=german_shepherd, gender=female, age=7)]

```