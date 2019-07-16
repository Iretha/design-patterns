---
layout: default
title: Creational Design Patterns
nav_order: 100
has_children: true
---

# Creational Design Patterns

The Creational Design Patterns deal with the way objects are created/ instantiated. 
{: .fs-6 .fw-300 }

---

## Types
* class-creational patterns ("creation through inheritance") - the "child" object inherits properties and behavior from the parent object
* object-creational patterns ("creation through delegation") - the object creation is delegated to another object

## Purpose
* to encapsulate knowledge about the concrete classes 
* to hide complexity from client 
* to hide from client how the object is created/ composed (client is decoupled from the initialization process)
* to allow us to replace the implementation with ease

## Patterns
There are 5 creational patterns introduced by GoF. 
Later we will add some more useful patterns to this list.
* [Abstract Factory (GoF)]()
* Builder Pattern (GoF)
* Factory Method (GoF)
* Prototype (GoF)
* Singleton (GoF)
* Object Pool (TODO)

### Abstract Factory aka "Factory of Factories"
* Designed to create families of objects without specifying their concrete classes
* The type will be determined at runtime.

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/abstract_factory)

### Builder Pattern
* Designed to improve the readability of the code when we have to build complex objects with a lot of optional fields
* Designed to ensure that the objects is always in a consistent state

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/builder)

### Factory Method
* Defines the interface, but lets subclasses to decide which concrete class to instantiate
* Refers to the newly created object through a common interface
* "Creation through inheritance"

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/factory_method)

### Prototype
* Faster object creation, when creating a new object is an expensive operation i.e. includes db calls with a lot or large data
* "Creation through delegation"

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/prototype)

### Singleton
* When we want to limit the object creation to only one instance
* When we want to control the access to that instance in serial way via the provided entry/ access point

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/singleton)

