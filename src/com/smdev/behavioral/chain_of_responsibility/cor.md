---
layout: default
title: Chain Of Responsibility (GoF)
parent: Behavioral Design Patterns
nav_order: 3000
permalink: /behavioral/chain-of-responsibility
---

# The Chain Of Responsibility Pattern (COR)

The Chain Of Responsibility Pattern is designed to decouple sender and receiver and to allow multiple handler implementations, that can handle the request and the concrete handler isn't known a priori.
{: .fs-6 .fw-300 }

---
## What problems does it solve?
It allows you to decouple sender and receiver. This means that the sender doesn't know how many 
handlers there are etc. Every handler has instance of another handler as a member. 
If it cannot proceed the request, it passes it to the next one.

Glossary:
* Handler - interfaces for handling requests
* Concrete Handler - concrete implementation of a handler
* Client - the one, who initiates the requests 

## Pros:
- To decouple Sender and Receiver
- To simplify the code from sender's point of view 
- To add/ remove handlers without affecting the sender
- To change handler dynamically

## Cons:
- No guarantee that the request will be handled
- Hard to "follow" the chain = the logic (when debugging or when trying to follow the logic behind the implementation)

## How to recognize it?
When you invoke a behavioral method, which invokes the same method in another implementation (type)

```java
    CoinHandler handler = new CoinHandler1d();
    handler.setNextHandler(new CoinHandler50c()).setNextHandler(new CoinHandler25c());
   
    handler.put(new Coin(1.75, 5.67)); // 25 cents
    handler.put(new Coin(2.15, 11.34)); // 50 cents
    handler.put(new Coin(1.75, 5.67)); // 25 cents
    
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
## Examples from Java API
```
java.util.logging.Logger#log()
javax.servlet.Filter#doFilter() - Servlet Filters in Java that allow multiple filters to process an HTTP request
```
## Scenarios
* To handle requests
* To "filter" requests
* To handle events
* When you have multiple implementations of some command and you (as sender) don't know 
  which one should handle the request. You (as receiver) can simply chain them and when a request is received
  you can pass it among the chained handlers, until one of them handles the request.
  
### Example 1 
We have a vending machine that accepts only coins from 1 Dollar, 50 Cents and 25 Cents.
When user puts a coin, we have a chain of coin handlers that try to "accept" the coin.
If a handler cannot recognize the coin, it forwards it to the next handler, until it's processed 
or returned back to the user.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/behavioral/chain_of_responisiblity) 


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
