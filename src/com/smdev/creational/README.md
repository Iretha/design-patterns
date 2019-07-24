---
layout: default
title: Creational Design Patterns
nav_order: 100
has_children: true
permalink: /creational
---

# Creational Design Patterns

The Creational Design Patterns deal with the way objects are created/ instantiated. 
{: .fs-6 .fw-300 }

---

## Types
* class-creational patterns ("creation through inheritance") - the object creation is done through inheritance
* object-creational patterns ("creation through delegation") - the object creation is delegated to another object

## Purpose
* to encapsulate knowledge about the concrete classes 
* to increase code readability
* to hide complexity from client 
* to hide how the object is created/ composed (client is decoupled from the initialization process)
* to make implementations interchangeable (can be replaced easy)
* to boost performance (by reusing instances)

## Patterns
There are 5 creational patterns introduced by GoF. 
Later we will add some more useful patterns to this list.

* [Abstract Factory (GoF)](https://iretha.github.io/design-patterns/creational/abstract-factory)

Designed to create families of objects without specifying their concrete classes

* [Builder Pattern (GoF)](https://iretha.github.io/design-patterns/creational/builder)

Designed to build complex objects with a lot of optional fields or when the input order of the values is not clear

* [Factory Method (GoF)](https://iretha.github.io/design-patterns/creational/factory-method)

Designed to create objects without specifying their concrete classes. Subclasses should decide which concrete class to instantiate.

* [Prototype (GoF)](https://iretha.github.io/design-patterns/creational/prototype)

Designed to create objects faster, when creating a new object is an expensive operation

* [Singleton (GoF)](https://iretha.github.io/design-patterns/creational/singleton)

Designed to control the number of instances (only 1) and the access to that instance in serial way via the provided entry/ access point

* [Factory (Pattern?)](https://iretha.github.io/design-patterns/creational/factory)

Designed to encapsulate the instantiation logic and to create objects without specifying their concrete classes.
Some people argue if this is a pattern or not, but we will mention it, because it's widely used.

* [Object Pool](https://iretha.github.io/design-patterns/creational/object-pool)

Designed to improve performance by reusing the instances of stateless objects, that are otherwise expensive to create.

* [Telescoping Constructor](https://iretha.github.io/design-patterns/creational/telescoping-constructor)

When you need a mechanism to provide default values or behavior when constructing objects. 

**Consider applying creational patterns when:**
- A set of related objects / families is designed to be used together 
or the system should be independent of how its objects and products are created (Abstract Factory)
- When you don't want to expose the concrete classes to the client (Factory/ Abstract Factory)
- You need to ensure that there is only one state of the object, you have many optional constructor args 
or you want to create an immutable object (Builder)
- The subclass should decide which concrete class it should use (Factory Method)
- When creating a new object is an expensive operation (Prototype)
- When you want to have only one instance or entry point to something and to coordinate actions across the system. (Singleton)
- When creating a new object is an expensive operation and you want to reuse instances or to limit the number of instances(Object Pool)