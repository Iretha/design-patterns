# GoF Design Patterns -> Creational Design Patterns -> Abstract Factory aka "Factory of Factories"

- Example_1 - Full example

## What problems does it solve? Why to use it?
Abstract Factory also known as "Factory of Factories" provides an interface for creating families of related or dependent objects 
without specifying their concrete classes. In other words, this model allows us to create objects 
that follow a general pattern.

## When to use it?
- The client is independent of how we create and compose the objects in the system
- The system consists of multiple families of objects, and these families are designed to be used together
- We need a run-time value to construct a particular dependency

## Pros:
- Client does't know the concrete classes

## Cons:
- To support the new type of objects will require changing the AbstractFactory class and all of its subclasses.

## How can be improved

## Examples from Java API
- TODO
- newInstance() of javax.xml.parsers.DocumentBuilderFactory class.

## Examples

### Example 1 - Full Example - How to implement it?
Let's say, we want to build some user interface, which consists of a text field and a button.
We may run our application in android mode or in swift mode, which will be determined at runtime.

To solve this problem, we need to:

1). Create our generic family classes
- InputText - generic input field
- Button - generic button
- UIFactory - generic ui components factory

2). Crete concrete classes for the "android" family classes
- AndroidInputText - android input field
- AndroidButton - android button
- AndroidUIFactory - android ui components factory

3). Crete concrete classes for the "swift" family classes
- SwiftInputText - android input field
- SwiftButton - android button
- SwiftUIFactory - android ui components factory

4). Create FactoryMaker class, which responsibility is to return the component factory, depending on the runtime type
```java
public class FactoryMaker {

    public static UIFactory getFactory(String choice) {
        UIFactory factory = null;
        if (choice.equals(AndroidUIFactory.TYPE)) {
            factory = new AndroidUIFactory();
        } else if (choice.equals(SwiftUIFactory.TYPE)) {
            factory = new SwiftUIFactory();
        }
        return factory;
    }
}
```

5). Example usage
```java
public class _Main {

    public static void main(String[] args) {
        runApp("Android");
        runApp("Swift");
    }

    private static void runApp(String type){
        UIFactory factory = FactoryMaker.getFactory(type);

        InputText input = factory.createInput();
        input.setValue("Hello, " + type);

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
```



