---
layout: default
title: Chain Of Responsibility
parent: Behavioral Design Patterns
nav_order: 3000
permalink: /behavioral/chain-of-responsibility
---

# The Chain Of Responsibility Design Pattern (COR)

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/chain_of_responisiblity) 

## What problems does it solve? Why to use it?
When you need to decouple the sender and the receiver. This means that the sender doesn't know how many 
handlers there are etc.

## When to use it?
When you have multiple implementators of some command and you (as sender) don't know 
which one should handle the request. You (as receiver) can simply chain them and when a request is received
you can pass it among the chained handlers, until one of them handles the request.

Every handler has instance of another handler as a member. 
If it cannot proceed the request, it passes it to the next one.

## Pros:
- Decouples Sender and Receiver
- Simplified from sender's point of view 
- Easier to extend and add new handlers
- You can dynamically change the members

## Cons:
- No guarantee that the request will be handled
- Hard to "follow" the chain = the logic (when debugging or when trying to follow the logic behind the implementation)

## Examples from Java API
Recognizable by behavioral methods which (indirectly) invokes the same method in another implementation of same abstract/interface type in a queue
```
java.util.logging.Logger#log()
javax.servlet.Filter#doFilter() - Servlet Filters in Java that allow multiple filters to process an HTTP request
```
## Examples
- To handle requests
- To "filter" 
- To handle events

It's like sieve with different holes (like shape and size).

### Example 1 - How to implement it?
We have a vending machine that accepts only coins from 1 Dollar, 50 Cents and 25 Cents.
When user puts a coin, we have a chain of coin handlers that try to "accept" the coin.
If a handler cannot recognize the coin, it forwards it to the next handler, until it's processed 
or returned back to the user.

1). Create a Coin class (this is our request data, we want to pass to the handlers)
```java
@ToString
public class Coin {

    /** In millimeters */
    @Getter
    private double diameter;

    /** In grams */
    @Getter
    private double weight;

    public Coin(double diameter, double weight) {
        this.diameter = diameter;
        this.weight = weight;
    }
}
```

2). Create a CoinHandler interface
```java
public interface CoinHandler {

    CoinHandler setNextHandler(CoinHandler nextHandler);

    boolean put(Coin coin);
}
```
3). Create an abstract generic handler, where we will keep the next handler
```java
public abstract class AbstractCoinHandler implements CoinHandler {

    @Getter
    private CoinHandler nextHandler;

    @Override
    public CoinHandler setNextHandler(CoinHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

}
```
4). Create the concrete handlers: 1 Dollar Handler / 50 Cents Handler / 25 Cents Handler
```java
/**
 * Handles only 1 Dollar Coins
 */
public class CoinHandler1d extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 2.58 && coin.getWeight() == 22.68) {
            System.out.println("1 Dollar " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}
```
```java
/**
 * Handles only 50 Cent Coins
 */
public class CoinHandler50c extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 2.15 && coin.getWeight() == 11.34) {
            System.out.println("50 Cents " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}
```
```java
/**
 * Handles only 25 Cent Coins
 */
public class CoinHandler25c extends AbstractCoinHandler {

    @Override
    public boolean put(Coin coin) {
        if (coin.getDiameter() == 1.75 && coin.getWeight() == 5.67) {
            System.out.println("25 Cents " + coin.toString() + " accepted by " + getClass().getSimpleName());
            return true;
        }
        return getNextHandler() != null ? getNextHandler().put(coin) : false;
    }
}

```
5) Create a Demo class
```java
public class _Main {

    public static void main(String[] args) {
        CoinHandler handler = new CoinHandler1d();
        handler.setNextHandler(new CoinHandler50c()).setNextHandler(new CoinHandler25c());

        Coin coin25c = new Coin(1.75, 5.67); // 25 cents
        put(coin25c, handler);

        Coin coin1d = new Coin(2.58, 22.68); // 1 dollar
        put(coin1d, handler);

        Coin coin10c = new Coin(1.35, 2.268); // 10 cents
        put(coin10c, handler);

        Coin coin50c = new Coin(2.15, 11.34); // 50 cents
        put(coin50c, handler);
    }

    private static void put(Coin coin, CoinHandler handler){
        boolean accepted = handler.put(coin);

        if(!accepted){
            System.out.println(coin.toString() + " not accepted! Please, insert another coin!");
        }
    }
}

```
Output:
```
25 Cents Coin(diameter=1.75, weight=5.67) accepted by CoinHandler25c
1 Dollar Coin(diameter=2.58, weight=22.68) accepted by CoinHandler1d
Coin(diameter=1.35, weight=2.268) not accepted! Please, insert another coin!
50 Cents Coin(diameter=2.15, weight=11.34) accepted by CoinHandler50c
```
