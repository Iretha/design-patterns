---
layout: default
title: Factory Method (GoF)
parent: Creational Design Patterns
nav_order: 1020
permalink: /creational/factory-method
---

# The Factory Method Design Pattern

GoF Design Patterns -> Creational Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/factory_method) 

## What problems does it solve? Why to use it?
- Defines the interface, but lets subclasses to decide which concrete class to instantiate
- Refers to the newly created object through a common interface
- The superclass specifies all standard and generic behavior, but let subclasses to implement the details
- Factory Method is "creation through inheritance"

## When to use it?
- Client doesn't know what concrete classes it will be required to create at runtime, 
but just wants to get a class that will do the job.
- Factory design pattern is used when we have a superclass with multiple sub-classes 
and based on the input, we need to return one of the sub-class.

## Pros:
- It hides the implementation details from the client. The client just need to have an object created via factory method.

## Cons:
- Makes code more difficult to read as all of your code is behind an abstraction that may in turn hide abstractions.
- Can be classed as an anti-pattern when it is incorrectly used

## Examples from Java API
Recognizeable by creational methods returning an implementation of an abstract/interface type
```
java.util.Calendar#getInstance()
java.util.ResourceBundle#getBundle()
java.text.NumberFormat#getInstance()
java.nio.charset.Charset#forName()
java.net.URLStreamHandlerFactory#createURLStreamHandler(String) (Returns singleton object per protocol)
java.util.EnumSet#of()
javax.xml.bind.JAXBContext#createMarshaller() and other similar methods
```

## Examples

### Example 1 - How to implement it?

TODO
