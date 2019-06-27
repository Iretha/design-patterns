# The Decorator Design Pattern

GoF Design Patterns -> Structural Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/structural/decorator) 

## What problems does it solve? Why to use it?

This is another way of adding additional behavior to objects instead of subclassing them.
The additional behavior can be added or removed at runtime.

## When to use it?

When objects may support multiple different features, but you want to choose some of them at runtime.

For example, when you are ordering pizza, you may want to choose what to have on it (cheese, paprika, mushrooms, tomato souse, garlic souse etc.).
You may make any combination you want. Instead of having so many concrete classes for every possible combination, 
you can implement the Decorator pattern and enable the features at runtime, based on the user input.

## Pros:
- Open for extension but closed for modification
- Behavior modification at runtime
- Reduces the number of subclasses
- Single Responsibility Principle - you can divide monolithic class into many small classes; adding a new class 
is better than modifying the existing once; 

## Cons:
- Too many small objects
- Complex instantiation of classes
- If there are too many decorators, it may gets hard to keep track of them (at compile- and runtime)
- Hard to implement if the order is important

## Examples from Java API
Recognizeable by creational methods taking an instance of same abstract/interface type which adds additional behaviour
```
All subclasses of java.io.InputStream, OutputStream, Reader and Writer have a constructor taking an instance of same type.
java.util.Collections, the checkedXXX(), synchronizedXXX() and unmodifiableXXX() methods.
javax.servlet.http.HttpServletRequestWrapper and HttpServletResponseWrapper
javax.swing.JScrollPane
```

## Examples

### Example 1
For example, when you are ordering pizza, you may choose what to have on it (cheese, paprika, tomato souse, garlic souse etc.).
You may choose any combination you want. Instead of having so many concrete classes for every possible combinations, 
you can you the Decorator pattern and enable the features, based on the user input.

How to implement it?

1). Create a Product class with some general behavior
```java
public interface Product {

    double getPrice();

}
```
2). Create Pizza.class that implements the general behavior
```java
public class Pizza implements Product {

    @Override
    public double getPrice() {
        return 0.6; // base price
    }
}
```
3). Create an abstract decorator, that should implement product and accept a product in it's constructor.
The product member is actually the base, we are going to decorate (extend with the concrete decorator)
```java
public abstract class AbstractDecorator implements Product {

    @Getter
    private Product base;

    public AbstractDecorator(Product base) {
        this.base = base;
    }

    @Override
    public double getPrice() {
        System.out.println("Adding " + getClass().getSimpleName());
        return this.base != null ? this.base.getPrice() : 0;
    }
}
```
4). Create some concrete decorators i.e. MushroomDecorator, which will add mushrooms to the base
```java
public class MushroomDecorator extends AbstractDecorator {

    public MushroomDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        return 0.5 + super.getPrice();
    }

}
```
5). Add some more decorators of your choice. You can see the full example with more decorators in github

6). Later you can even extend with packaging and home delivery by adding more decorators

7). Create a client for demonstration purposes
```java
public class _Main {

    public static void main(String[] args) {

        Product pizza1 = new ItalianDoughDecorator(new TomatoSouceDecorator(new SalamiDecorator(new MushroomDecorator(new CheeseDecorator(new BbqSouceDecorator(new Pizza()))))));
        System.out.println("=> Total Price: " + pizza1.getPrice());
        System.out.println("");

        Product pizza2 = new RegularDoughDecorator(new TomatoSouceDecorator(new MushroomDecorator(new MushroomDecorator(new CheeseDecorator(new BbqSouceDecorator(new Pizza()))))));
        System.out.println("=> Total Price: " + pizza2.getPrice());
        System.out.println("");


        Product pizzaForHome = new PackageDecorator(pizza1);
        System.out.println("=> Total Price: " + pizzaForHome.getPrice());
        System.out.println("");


        Product pizzaForHomeWithDelivery = new DeliveryDecorator(new PackageDecorator(pizza2));
        System.out.println("=> Total Price: " + pizzaForHomeWithDelivery.getPrice());
        System.out.println("");
    }
}
```
Output:
```
Adding ItalianDoughDecorator
Adding TomatoSouceDecorator
Adding SalamiDecorator
Adding MushroomDecorator
Adding CheeseDecorator
Adding BbqSouceDecorator
=> Total Price: 10.01

Adding RegularDoughDecorator
Adding TomatoSouceDecorator
Adding MushroomDecorator
Adding MushroomDecorator
Adding CheeseDecorator
Adding BbqSouceDecorator
=> Total Price: 5.82

Adding PackageDecorator
Adding ItalianDoughDecorator
Adding TomatoSouceDecorator
Adding SalamiDecorator
Adding MushroomDecorator
Adding CheeseDecorator
Adding BbqSouceDecorator
=> Total Price: 10.81

Adding DeliveryDecorator
Adding PackageDecorator
Adding RegularDoughDecorator
Adding TomatoSouceDecorator
Adding MushroomDecorator
Adding MushroomDecorator
Adding CheeseDecorator
Adding BbqSouceDecorator
=> Total Price: 12.42
```

### Example 2 
In games, while you are creating your character, you can choose different features to be applied to it.
For instance - to have beard, earrings, markings etc. You can make different combinations, some of them might be never
done before. In general your character can support everything, but we don't need everything, only the chosen ones. 
During the game play you can also receive items or earn new abilities, that depend on some external conditions in the game play.

### Example 3
For example, people have different clothes or accessories, which are more appropriate for specific whether conditions.
If it's raining, you can choose to wear a raincoat or take your umbrella. You have a raincoat, 
you can wear it all the time, but you don't want to. You want to decide "at runtime" to "apply the feature" or not.
Same thing applies for umbrella as well and sometimes you may even choose to use the umbrella and the raincoat together.


