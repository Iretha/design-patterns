# GoF Design Patterns (23)

A design pattern is reusable solution of a commonly occurring problem (usually in software design). 

Design Patterns got very popular when the book "Design Patterns: Elements of Reusable Object-Oriented Software (1994)" 
was published. The book was written by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides (known as the "Gang of Four" / GoF).
The book is divided into two parts, with the first two chapters exploring the capabilities and pitfalls of 
object-oriented programming, and the remaining chapters describing 23 classic software design patterns.

Design patterns were originally grouped into the categories: 
## [Creational Design Patterns (5)](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational)

Creational Design Patterns have to deal with object creation/ instantiation. They reduce complexity and hide 
from client how the object is created. The client is entirely decoupled from the actual initialization process
and the implementation could be easily replaced if necessary.

**Consider applying creational patterns when:**
- A set of related objects / families is designed to be used together 
or the system should be independent of how its objects and products are created (Abstract Factory)
- You need to ensure that there is only one state of the object, you have many optional constructor args 
or you want to create an immutable object (Builder)
- The subclass should decide which concrete class it should use (Factory Method)
- When creating a new object is an expensive operation (Prototype)
- When you want to have only one entry point to something and to coordinate actions across the system. (Singleton)

## [Structural Design Patterns (7)](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/structural)
Structural Design Patterns have to deal with the relations between components and objects. 
They simplify the structure by identifying the relationships and ease the design by identifying a simple way to realize relationships among entities.

**Consider applying structural patterns when:**
- Need to adapt incompatible interfaces (Adapter)
- You have to deal with two different hierarchies (Bridge)
- You have tree-like structure with same general behavior (Composite)
- You need to add/ remove behavior at runtime (Decorator)
- You need to simplify and hide complexity (Facade)
- You have objects that share same data and you need to lower application memory usage (Flyweight)
- You have to protect some object by controlling or managing access to it; to cache or log requests/ responses (Proxy)

## [GoF Behavioral Design Patterns (11)](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral)

Behavioral design patterns are design patterns that identify common communication patterns between objects. 
By doing so, these patterns increase flexibility in carrying out this communication.

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

## Useful Links
[Design Patterns: Elements of Reusable Object-Oriented Software - The Book](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612/ref=sr_1_1?keywords=Design+Patterns%3A+Elements+of+Reusable+Object-Oriented+Software&qid=1561537399&s=books&sr=1-1)

[Wikipedia: Design Pattern](https://en.wikipedia.org/wiki/Software_design_pattern)

[Wikipedia: Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)

[Wikipedia: Creational Design Patterns](https://en.wikipedia.org/wiki/Creational_pattern)

[Wikipedia: Structural Design Patterns](https://en.wikipedia.org/wiki/Structural_pattern)

[Wikipedia: Behavioral Design Patterns](https://en.wikipedia.org/wiki/Behavioral_pattern)

[StackOverflow: Design Patterns in Java API/ Java Core](https://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns-in-javas-core-libraries)