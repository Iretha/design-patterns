# GoF Design Patterns -> Creational Design Patterns -> Abstract Factory aka "Factory of Factories"

- Example_1 - Full example, with the best practises applied to it
- Example_2 - Lombok implementation of the Builder Pattern (very easy to use)

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


