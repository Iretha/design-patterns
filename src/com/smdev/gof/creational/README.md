---
layout: default
title: Creational Design Patterns
parent: TODO
nav_order: 100
has_children: true
permalink: /todo/1
---

# Creational Design Patterns (5)

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

