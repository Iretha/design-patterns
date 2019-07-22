---
layout: default
title: Interview Questions
nav_order: 8888
description: ""
permalink: /interview-questions
---

# Interview Questions

## General Questions
 
### What are Design Patterns?
Each design pattern offers a reusable solution for a for common (repeatable) design problem.

### What is Gang of Four (GOF)?
The authors of the book “Design Patterns: Elements of Reusable Object-Oriented Software (1994)”

### Name the types of the Design Patterns
There are many types, but if the question is about the fundamental patterns like GoF Design Patterns, there
are 3 types: creational, structural and behavioral. If the question is about all the patterns, some of they are:
architectural, integration etc.

### Explain the idea behind Creational Patterns
TODO

### Explain the idea behind Structural Patterns
TODO

### Explain the idea behind Behavioral Patterns
TODO

### Name some of the design patterns, used in JAVA API.
#### Factory Method: 
```java 
Calendar.getInstance()
```
#### Builder: 
```java
Calendar cal = new Calendar.Builder()
    .setCalendarType("iso8601").setWeekDate(2013, 1, MONDAY).build();
```
```java
Locale aLocale = new Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build(); (since java 7)
```
#### Adapter:
```java
java.util.Arrays#asList()
```
#### Decorator:
```java
All subclasses of java.io.InputStream, OutputStream, Reader and Writer have a constructor taking an instance of same type.
```
#### Proxy:
```java
java.lang.reflect.Proxy
```
#### Chain of Responsibility:
```java
javax.servlet.Filter#doFilter() - Servlet Filters in Java that allow multiple filters to process an HTTP request
```
#### Command:
```java
java.lang.Runnable
```
#### Iterator:
```java
All implementations of java.util.Iterator (thus among others also java.util.Scanner!).
All implementations of java.util.Enumeration
```
#### Mediator:
```java
java.util.Timer (all scheduleXXX() methods)
```
#### Strategy:
```java
java.util.Comparator#compare(), executed by among others Collections#sort()
```


## Singleton Questions
### Can we create a clone of a singleton object?
Yes, we can.

### How to prevent cloning of a singleton object?
Implement the Cloneable interface and make clone() method to do nothing or to throw an Exception

### Singleton with double checked locking - what is that and how is implemented?
Does the check twice. You can also mark the instance as volatile (to indicate that a variable's value will be modified by different threads)
```java
    public static Singleton getInstance() {
        if (instance == null) { // First Check
            synchronized(Singleton.class) {
                if (instance == null) { // Second check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
```

### How to make Singleton serializable?
> Implement the Serializable interface. The problem is that when we deserialize it, it will create a new instance of the class.
> This will be solved by implementing readResolve or if you use enum.
```java
protected Object readResolve() {
    return getInstance();
}
```

### What are the differences between a static class and a singleton class?
* The Singleton is object and can be passed as parameter, while the static class can not.
* The Singleton can implement interfaces, extend other classes, while the static class can not.
* The Singleton is stored in the heap (as the other objects), while statics are stored in the stack.

## Comparison Questions
### Adapter vs Decorator vs Facade vs Proxy - Explain the differences
* Adapter: Makes two incompatible interfaces compatible
* Decorator: Adds functionality based on some input or configuration
* Facade: The facade provides a different front to the client and hides complexity 
* Proxy: May provide some functionality like logging etc.





