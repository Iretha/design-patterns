---
layout: default
title: Private Class Data
parent: Structural Design Patterns
nav_order: 2070
permalink: /structural/private-class-data
---

# The Private Class Data Design Pattern

The Private Class Data Pattern is designed to protect the object data even from it's own methods.
{: .fs-6 .fw-300 }

---

The Private Class Data Pattern is also knows as PIMPL (Private IMPLementation) or Opaque pointer.

## What problems does it solve?
The Private Class Data prevents manipulation of data, when modification is no longer desirable (i.e. after construction). 
The pattern is used, where "final" is not applicable. Restricting manipulation is done by wrapping the restricted class
variables in a a single Data Object (a data wrapper class). 

**The key point is that the write access to the data is restricted even for own methods.**

## Pros:
- to encapsulate class data initialization
- to encapsulate data and data manipulation (by separating data from methods, that use it)
- to restrict access
- to restrict data manipulation after specific point of time
- to control data state
- to provide new type of "final" ("final after constructor")

## Cons:
- increases overall complexity of the code
- additional classes (types)

## How to recognize it?
* There is a data wrapper as a member of the class with a private visibility and the members of the wrapper 
are not visible and there are no setters.

## Scenarios

* To create immutable types

* To ensure single/ consistent state of some object

* To make a object read-only
  
### Example 1

We want to create an immutable type and add an extra level of security by preventing the write access of the methods 
of same type. 

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/private-class-data) 

1). Create a PersonData class
```java
import lombok.ToString;
import java.util.Collections;
import java.util.List;

@ToString
public class PersonData {
    private String name;
    private int age;
    private List<String> addresses;

    public PersonData(String name, int age, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.addresses = addresses;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public List<String> getAddresses() {
        return Collections.unmodifiableList(this.addresses);
    }
}
```
2). Create Immutable Person
```java
import java.util.List;

public class ImmutablePerson {

    private final PersonData data;

    public ImmutablePerson(String name, int age, List<String> addresses) {
        this.data = new PersonData(name, age, addresses);
    }

    public void doSomething(){
        System.out.println(this.data);
    }
}
```
3). Create demo class
```java
public class _Main {

    public static void main(String[] args) {
        ImmutablePerson person1 = new ImmutablePerson("Jon Snow", 35, Arrays.asList("London", "Los Angeles"));
        person1.doSomething();
    }
}
```
Output:
```java
PersonData(name=Jon Snow, age=35, addresses=[London, Los Angeles])
```