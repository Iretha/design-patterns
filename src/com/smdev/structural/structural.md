---
layout: default
title: Structural Design Patterns
nav_order: 200
has_children: true
permalink: /structural
---

# Structural Design Patterns (7)

**Consider applying structural patterns when:**
- Need to adapt incompatible interfaces (Adapter)
- You have to deal with two different hierarchies (Bridge)
- You have tree-like structure with same general behavior (Composite)
- You need to add/ remove behavior at runtime (Decorator)
- You need to simplify and hide complexity (Facade)
- You have objects that share same data and you need to lower application memory usage (Flyweight)
- You have to protect some object by controlling or managing access to it; to cache or log requests/ responses (Proxy)


### Adapter
* The adapter pattern makes two incompatible interfaces compatible without changing their existing code.
* Adapters do not extend functionality, they just make two things compatible.

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/structural/adapter)

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

