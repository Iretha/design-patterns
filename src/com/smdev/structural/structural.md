---
layout: default
title: Structural Design Patterns
nav_order: 200
has_children: true
permalink: /structural
---

# Structural Design Patterns

The Structural Design Patterns deal with the relations between components and objects. 
{: .fs-6 .fw-300 }

---

## Types
* structural class patterns - uses inheritance
* structural objects patterns - uses composition

## Purpose
* Best way to compose objects and to form larger structures

## Patterns
There are 7 structural patterns introduced by GoF. 
Later we will add some more useful patterns to this list.

* [Adapter (GoF)](/design-patterns/structural/adapter)

Designed to make two incompatible types compatible without changing their existing code or extending their functionality

* [Bridge (GoF)](/design-patterns/structural/bridge)

Designed to separate object abstraction from the implementation

* [Composite (GoF)](/design-patterns/structural/composite)

Designed to build a class hierarchy from primitive and composite objects, that may represent complex structures

* [Decorator (GoF)](/design-patterns/structural/decorator)

Designed to add responsibilities to objects dynamically

* [Facade (GoF)](/design-patterns/structural/facade)

Designed to represent an entire subsystem as a single object

* [Flyweight (GoF)](/design-patterns/structural/flyweight)

Designed to share objects to for better efficiency or consistency

* [Proxy (GoF)](/design-patterns/structural/proxy)

Designed to provide a level of indirection to object members

* [Private Class Data](/design-patterns/structural/private-class-data)

Designed to protect the object data even from it's own methods.

**Consider applying structural patterns when:**
- Need to adapt incompatible interfaces (Adapter)
- You have to deal with two different hierarchies (Bridge)
- You have tree-like structure with same general behavior (Composite)
- You need to add/ remove behavior at runtime (Decorator)
- You need to simplify and hide complexity (Facade)
- You have objects that share same data and you need to lower application memory usage (Flyweight)
- You have to protect some object by controlling or managing access to it; to cache or log requests/ responses (Proxy)




### Bridge
* If your class does things that can be done independently and doesn't actually care how they work and 
how they are implemented, then you MUST move them out of this class, maybe to separate hierarchy 
and use the functionality you need referring the abstraction (interface), not the implementation (classes) itself
* Applies "composition over inheritance" concept
* Decouples abstraction from it's implementation, so that the two can live independently
* Reduces the number of sub classes

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/bridge)

### Composite
* The pattern could be used to implement a tree-like, hierarchical structure of elements, that have same behavior. 
* Client knows only about the general behavior off all components and treats them the same way.
* It is hard to restrict the components of the composite

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/composite)

### Decorator
* This is another way of adding additional behavior to objects instead of subclassing them.
* The additional behavior can be added or removed at runtime.

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/decorator)

### Facade
* Provides simple interface for client and hides complexity
* Reduces dependencies if used for modules/ subsystems

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/facade)

### Flyweight
* Lowers the memory usage of your application by decreasing duplicated data (even object instances)
* Used for caching

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/flyweight)

### Proxy
* To check user privileges/ permissions and restrict access
* To add logging
* To cache objects
* To lock/ unlock object
* To route/ forward request, based on some rules

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/proxy)

