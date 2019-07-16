---
layout: default
title: Behavioral Design Patterns
nav_order: 300
has_children: true
permalink: /behavioral
---

# Behavioral Design Patterns (11)

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


### Chain Of Responsibility
* Decouples Sender & Receiver
* Chain can be "modified" at runtime by changing the members
* Does not guarantee that the request will be handled

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/chain_of_responsibility)

### Command
* When you need to decouple the Invoker and the Receiver and let them "talk" via commands
* The only handler is the Receiver (the only one, who knows hot to perform the commands)

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/command)

### Interpreter
* To build a language with a simple grammar (simple search engines)
* To produce different outputs of the same thing

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/interpreter)

### Iterator
* When you need to traverse some custom collection of elements sequentially, based on specific business rule
* If you need more complex way to traverse elements, than the provided inner iterators

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/iterator)

### Mediator
* If you need to refactor some tight coupled components, that interact together
* If you need to centralize the communication between components

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/mediator)

### Memento
* If you need to restore the state of an objects

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/memento)

### Observer / Subscribe-Publish 
* If you need to notify multiple observers, when something changes

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/observer)

### State
* When an object has different states and has absolutely different behavior in each of the states (like metamorphosis or transformation)
* When objects transforms into another object, when something changes/ happens (like when an egg hatches and becomes a bird)

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/state)

### Strategy
* If you have many classes with similar behavior, you can extract the "behaviour"/ algorithm in separate class and reuse it
* If you want to "switch" the behavior
* If you have an objects that can "act" differently

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/strategy)

### Template Method
* When multiple classes have similar behavior with slight differences
* When multiple classes share almost same algorithm
* When refactoring classes with duplicated code

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/template_method)

### Visitor
* To separate the algorithm from the object

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/visitor)
