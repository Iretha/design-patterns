# The Flyweight Design Pattern

GoF Design Patterns -> Structural Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/structural/flyweight) 

## What problems does it solve? Why to use it?
It solves memory issues and lowers the needed memory. 
You can optimize the memory and lower RAM usage by dividing the objects into two parts:
- shareable (intrinsic) state 
- non-shareable (extrinsic) state.
Then objects with common data can use the same shared state, instead of multiplicating the common data for/ in each object.

Glossary:
- Flyweight - holds/ represents the shareable (intrinsic/ immutable) state of an object
- FlyweightFactory - holds all Flyweight objects (like cache) and "creates" new Flyweight object (if not available in cache or returns the available)
- Concrete object - holds/ represents the non-shareable (extrinsic/ mutable) state. This is the state that changes over time or is unique for the object
- Client - the one, who uses the objects with their shareable and non-shareable states

## When to use it?
- When you have common data that is shared between multiple objects
- When the application creates large number of similar objects
- When you need to reduce the storage cost of the application

## Pros:
- Lowers the memory usage of your application by decreasing duplicated data (even object instances)

## Cons:
- Increases the complexity of the code
- There is an overhead if you need to recalculate/recreate/sync the shareable (intrinsic) state of the object for some reason

## Examples from Java API
Recognizeable by creational methods returning a cached instance instead of a new one
```
java.lang.Integer#valueOf(int) (also on Boolean, Byte, Character, Short, Long and BigDecimal)
```
## Examples

### Example 1

