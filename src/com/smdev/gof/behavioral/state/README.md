# The State Design Pattern

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/state) 

## What problems does it solve? Why to use it?
To simplify the code by separating the logic, if an object behaves differently in each of it's states.
This is done by creating different class for each state and when the state changes, we should instantiate different
concrete class that will represent the object and it's state.
It it's like transforming one object into another when something happens.

Glossary:
- Context - holds the general data (stateless data) and the current state of the object; it will be used by clients
- Abstract State - defines the general behavior; receives the context and "knows" which is the next state and how to switch to it
- Concrete state - implements the concrete behavior for the concrete state

## When to use it?
- When an object has different states and has absolutely different behavior in each of the states
- To implement a state machine - based on some input, it will switch to a different state and each state will specify the next state (again, based on the input)
- To implement a process

## Pros:
- Single Responsibility Principle
- Simplifies the code
- To implement polymorphic behavior of an object
- Easy to extend and add new states

## Cons:
- It can be an overhead if there are only a few states
- Hard to maintain because you need different class for each object + state

## Examples from Java API
Recognizable by behavioral methods which invoke a method on an instance of another abstract/interface type, depending on own state
```
java.util.Observer/java.util.Observable (rarely used in real world though)
All implementations of java.util.EventListener (practically all over Swing thus)
javax.servlet.http.HttpSessionBindingListener
javax.servlet.http.HttpSessionAttributeListener
javax.faces.event.PhaseListener

```
## Examples
It's like a butterfly metamorphosis. 
There are 4 stages from an egg to a butterfly, When changes it's state, it also changes it's behavior.
1). The egg - does not have legs, wings, can't walk, eat or fly. When the egg hatches, it becomes a larva.
2). The Larva - they grow, they does not have wings, can't fly, they molt (change their skin). When is's fully grown, they form a pupa.
3). The Pupa - in pupa (larva in something like egg) they undergo the real metamorphosis - the body parts are transformed, grow new body parts as well until they are fully formed
4). The Butterfly - can fly etc.

Or when an egg hatches and becomes a chickling and later bird.
### Example 1 - How to implement it?
