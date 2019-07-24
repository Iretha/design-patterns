---
layout: default
title: Behavioral Design Patterns
nav_order: 300
has_children: true
permalink: /behavioral
---

# Behavioral Design Patterns

The Behavioral Design Pattern deal with the communication between components and objects. 
{: .fs-6 .fw-300 }

---
## Types
* behavioral class patterns - use inheritance (template method, interpreter only)
* behavioral objects patterns - use composition

## Purpose
* Best way to handle communication between objects

## Patterns
There are 11 behavioral patterns introduced by GoF. 
Later we will add some more useful patterns to this list.

* [Chain Of Responsibility (GoF)](https://iretha.github.io/design-patterns/behavioral/chain-of-responsibility)

Designed to to decouple sender and receiver and to allow multiple handler implementations, that can handle the request, especially when the concrete handler isn't known a priori

* [Command (GoF)](https://iretha.github.io/design-patterns/behavioral/command)

Designed to encapsulate the data, needed to perform the action as an object, and execute the action. Sometimes action can be stored in a queue and executed later. 

* [Interpereter (GoF)](https://iretha.github.io/design-patterns/behavioral/command)

Designed to define a representation of the grammar of a given language und use that representation to interpret sentences in that language

* [Iterator (GoF)](https://iretha.github.io/design-patterns/behavioral/iterator)

Designed to give access to the elements of an aggregate object sequentially without exposing their underlying representation

* [Mediator (GoF)](https://iretha.github.io/design-patterns/behavioral/mediator)

Designed to encapsulate the communication between multiple objects in a single object in order to reduce the direct relations between them

* [Memento (GoF)](https://iretha.github.io/design-patterns/behavioral/memento)

Designed to externalize the internal state of the object in order to be stored or to restore an object to some previous version of its state

* [Observer (GoF)](https://iretha.github.io/design-patterns/behavioral/observer)

Designed to define one-to-many dependency between objects, so that when one object changes its state, all its dependents get notified and updated automatically

* [State (GoF)](https://iretha.github.io/design-patterns/behavioral/state)

Designed to change the object class (type), when object state changes, because its behavior also changes

* [Strategy (GoF)](https://iretha.github.io/design-patterns/behavioral/strategy)

Designed to separate the behavior from it's host class, by encapsulating the algorithm in a separate class. 
This allows you to implement many algorithms and make them interchangeable.

* [Template Method (GoF)](https://iretha.github.io/design-patterns/behavioral/template-method)

Designed to define the skeleton of an algorithm, but lets subclasses to implement or override separate parts of it

* [Visitor (GoF)](https://iretha.github.io/design-patterns/behavioral/visitor)

Designed to separate the algorithm from the object and to apply it to a group of similar types

* [Null Object](https://iretha.github.io/design-patterns/behavioral/null-object)

Designed to provide a default behavior if the object is not found

**Consider applying behavioral patterns when:**
- When you have multiple handlers with no specific priority and they should handle requests, based on some rules or forward them (Chain of Responsibility)
- When you have many options handled by a single handler (Command)
- When you have multiple outputs of the same thing or you want to create a domain language to support simple searches like "find dogs where breed eq german_shepherd and gender eq female" (Interpreter)
- When you need to traverse some custom collection of elements sequentially, based on some business rule (Iterator)
- When a change in one component leads to changes/ updates in other components (Mediator)
- When you have to implement revert/ undo/ restore/ rollback to some previous state (Memento) 
- When you need to notify multiple observers, when something changes/ happens (Observer)
- When objects act differently (like different object) in different states and they should be transformed into new objects (State) /each state knows the next state, the client doesn't know them/
- When you have many classes with the same behavior (implement the same algorithm) or the behavior should be easily changed (Strategy) /the client should know the available strategies and to use them/
- When many classes share almost the same behavior or algorithm (Template Method)
- When you don't want to change the code or you want the algorithm to be in a separate class (Visitor)
- When you want to provide default behavior, when there is no object (Null Object)
