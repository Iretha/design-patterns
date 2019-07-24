Visit [https://iretha.github.io/design-patterns](https://iretha.github.io/design-patterns)
for detailed explanation and code examples.

# Design Patterns Cheat List

## Creational

### [Abstract Factory (GoF)](https://iretha.github.io/design-patterns/creational/abstract-factory)

Designed to create families of objects without specifying their concrete classes

### [Builder Pattern (GoF)](https://iretha.github.io/design-patterns/creational/builder)

Designed to build complex objects with a lot of optional fields or when the input order of the values is not clear

### [Factory Method (GoF)](https://iretha.github.io/design-patterns/creational/factory-method)

Designed to create objects without specifying their concrete classes. Subclasses should decide which concrete class to instantiate.

### [Prototype (GoF)](https://iretha.github.io/design-patterns/creational/prototype)

Designed to create objects faster, when creating a new object is an expensive operation

### [Singleton (GoF)](https://iretha.github.io/design-patterns/creational/singleton)

Designed to control the number of instances (only 1) and the access to that instance in serial way via the provided entry/ access point

### [Factory (Pattern?)](https://iretha.github.io/design-patterns/creational/factory)

Designed to encapsulate the instantiation logic and to create objects without specifying their concrete classes.
Some people argue if this is a pattern or not, but we will mention it, because it's widely used.

### [Object Pool](https://iretha.github.io/design-patterns/creational/object-pool)

Designed to improve performance by reusing the instances of stateless objects, that are otherwise expensive to create.

### [Telescoping Constructor](https://iretha.github.io/design-patterns/creational/telescoping-constructor)

When you need a mechanism to provide default values or behavior when constructing objects. 

## Structural
### [Adapter (GoF)](https://iretha.github.io/design-patterns/structural/adapter)

Designed to make two incompatible types compatible without changing their existing code

### [Bridge (GoF)](https://iretha.github.io/design-patterns/structural/bridge)

Designed to separate object abstraction from the implementation, so that you can change implementation without affecting the rest of the code

### [Composite (GoF)](https://iretha.github.io/design-patterns/structural/composite)

Designed to build a class hierarchy from primitive and composite objects, that may represent complex structures, but have similar behavior

### [Decorator (GoF)](https://iretha.github.io/design-patterns/structural/decorator)

Designed to to add/ remove responsibilities to objects dynamically (even at runtime), without changing their implementation

### [Facade (GoF)](https://iretha.github.io/design-patterns/structural/facade)

Designed to hide an entire subsystem behind a facade and present it is as a single object/ interface

### [Flyweight (GoF)](https://iretha.github.io/design-patterns/structural/flyweight)

Designed to share objects (to reuse instances) for better efficiency and consistency

### [Proxy (GoF)](https://iretha.github.io/design-patterns/structural/proxy)

Designed to provide a level of indirection to object members and may add additional logic (i.e. to control access or to provide a wrapper implementation for better performance)

### [Private Class Data](https://iretha.github.io/design-patterns/structural/private-class-data)

Designed to protect the object data (to restrict write access) even from it's own methods.

## Behavioral

### [Chain Of Responsibility (GoF)](https://iretha.github.io/design-patterns/behavioral/chain-of-responsibility)

Designed to to decouple sender and receiver and to allow multiple handler implementations, that can handle the request, especially when the concrete handler isn't known a priori

### [Command (GoF)](https://iretha.github.io/design-patterns/behavioral/command)

Designed to encapsulate the data, needed to perform the action as an object, and execute the action. Sometimes action can be stored in a queue and executed later. 

### [Interpereter (GoF)](https://iretha.github.io/design-patterns/behavioral/command)

Designed to define a representation of the grammar of a given language und use that representation to interpret sentences in that language

### [Iterator (GoF)](https://iretha.github.io/design-patterns/behavioral/iterator)

Designed to give access to the elements of an aggregate object sequentially without exposing their underlying representation

### [Mediator (GoF)](https://iretha.github.io/design-patterns/behavioral/mediator)

Designed to encapsulate the communication between multiple objects in a single object in order to reduce the direct relations between them

### [Memento (GoF)](https://iretha.github.io/design-patterns/behavioral/memento)

Designed to externalize the internal state of the object in order to be stored or to restore an object to some previous version of its state

### [Observer (GoF)](https://iretha.github.io/design-patterns/behavioral/observer)

Designed to define one-to-many dependency between objects, so that when one object changes its state, all its dependents get notified and updated automatically

### [State (GoF)](https://iretha.github.io/design-patterns/behavioral/state)

Designed to change the object class (type), when object state changes, because its behavior also changes

### [Strategy (GoF)](https://iretha.github.io/design-patterns/behavioral/strategy)

Designed to separate the behavior from it's host class, by encapsulating the algorithm in a separate class. 
This allows you to implement many algorithms and make them interchangeable.

### [Template Method (GoF)](https://iretha.github.io/design-patterns/behavioral/template-method)

Designed to define the skeleton of an algorithm, but lets subclasses to implement or override separate parts of it

### [Visitor (GoF)](https://iretha.github.io/design-patterns/behavioral/visitor)

Designed to separate the algorithm from the object and to apply it to a group of similar types

### [Null Object](https://iretha.github.io/design-patterns/behavioral/null-object)

Designed to provide a default behavior if the object is not found