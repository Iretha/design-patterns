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
* structural class patterns - use inheritance
* structural objects patterns - use composition

## Purpose
* Best way to compose objects and to form larger structures

## Patterns
There are 7 structural patterns introduced by GoF. 
Later we will add some more useful patterns to this list.

Design Pattern | Main Purpose
--- | ---
[Adapter (GoF)](https://iretha.github.io/design-patterns/structural/adapter) | Designed to make **two incompatible types compatible** without changing their existing code
[Bridge (GoF)](https://iretha.github.io/design-patterns/structural/bridge) | Designed **to separate object abstraction from the implementation**, so that you can change implementation without affecting the rest of the code
[Composite (GoF)](https://iretha.github.io/design-patterns/structural/composite) | Designed **to build a class hierarchy from primitive and composite objects**, that may represent complex structures, but have similar behavior
[Decorator (GoF)](https://iretha.github.io/design-patterns/structural/decorator) | Designed **to add/ remove independent responsibilities to objects dynamically** (even at runtime), without changing their implementation
[Facade (GoF)](https://iretha.github.io/design-patterns/structural/facade) | Designed **to hide an entire subsystem behind a facade** and present it is as a single object/ interface
[Flyweight (GoF)](https://iretha.github.io/design-patterns/structural/flyweight) | Designed **to share objects (to reuse instances)** for better efficiency and consistency
[Proxy (GoF)](https://iretha.github.io/design-patterns/structural/proxy) | Designed **to provide a level of indirection to object members and may add additional logic** (i.e. to control access or to provide a wrapper implementation for better performance)
[Private Class Data](https://iretha.github.io/design-patterns/structural/private-class-data) | Designed to **protect the object data (to restrict write access)** even from it's own methods.


**Consider applying structural patterns when:**
- Need to adapt incompatible interfaces (Adapter)
- You have to deal with two different hierarchies or to decouple abstraction from implementation (Bridge)
- You have tree-like structure with same general behavior (Composite)
- You need to add/ remove behavior to specific instances (not the class) at runtime (Decorator)
- You need to simplify usage and hide complexity (Facade)
- You have objects that share same data, many instances with the same values, and you need to lower application memory usage (Flyweight)
- You have to protect some object by controlling or managing access to it; to cache or log requests/ responses (Proxy)
- You have to implement an immutable object and restrict even his own methods from modifying it's data (Private Class Data)