---
layout: default
title: Abstract Factory
parent: Creational Design Patterns
nav_order: 1000
permalink: /creational/abstract-factory
---

# Abstract Factory (aka Kit, Factory of Factories)

The Abstract Factory Design Pattern is designed to create families of objects without specifying their concrete classes.
{: .fs-6 .fw-300 }

The Abstract Factory is also known as "Kit" or "Factory of Factories". It uses "creation through delegation" method and 
provides an interface for creating families of related or dependent objects without specifying 
their concrete classes. In other words, this model allows us to create objects that follow a general pattern.
{: .fs-6 .fw-90 }

---

## What problems does it solve? 
- To construct a particular dependency at runtime, depending on some parameter or setting
- To hide the concrete classes/ the implementation from the client
- To support multiple families of objects
- To enforce the constraint that objects of a single family are designed to work together
- The application can work with only one family at a time

## Pros:
- Creates objects without exposing the instantiation logic to the client.
- Loose coupling - the client does't know the concrete classes
- Easy to add/ remove families

## Cons:
- To add new features (members, methods and etc.) - we have to modify the generic classes
and add implementation for each family

## How to recognize it
When you pass a parameter to a creational method and the method returns instance of a factory,
that will be used to create instances of other types (the family objects). If you pass another parameter, 
you will get another factory (for another family of objects).

## Scenarios
- If you want to provide some generic model of something, that may have different implementations
- If you want to "change" the implementation of something with ease
- If you want to "switch" between different implementations
- If you want to support multiple platforms while keeping your code-base unified, but you still should have 
different implementation for each OS 

## Examples from Java API
```
- javax.xml.parsers.DocumentBuilderFactory#newInstance()
- javax.xml.transform.TransformerFactory#newInstance()
- javax.xml.xpath.XPathFactory#newInstance()
```

## Examples
### Example 1
[Source Code on Github](https://github.com/Iretha/design-patterns/tree/master/src/com/smdev/creational/abstract_factory)

Let's say, we want to build some user interface, which consists of a text field and a button.
We may run our application in android mode or in swift mode, which will be determined at runtime.

To solve this problem, we need to:

1) Create our generic family classes
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
        if (choice.equals("Android")) {
            factory = new AndroidUIFactory();
        } else if (choice.equals("Swift")) {
            factory = new SwiftUIFactory();
        }
        return factory;
    }
}
```

OR We can use enumeration to return the factory, which is needed

```java
public enum Platform {
    ANDROID,
    SWIFT;

    public UIFactory getFactory() {
        UIFactory factory = null;
        switch (this) {
            case ANDROID:
                factory = new AndroidUIFactory();
                break;
            case SWIFT:
                factory = new SwiftUIFactory();
                break;
            default:
                break;
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

OR if we use ENUM
```java
public class _Main {

    public static void main(String[] args) {
        initApp(Platform.ANDROID);
        initApp(Platform.SWIFT);
    }

    private static void initApp(Platform platform) {
        UIFactory factory = platform.getFactory();

        InputText input = factory.createInput();
        input.setValue("Hello, " + platform.name());

        Button button = factory.createButton();
        button.click();

        input.submit();
    }
}
```



