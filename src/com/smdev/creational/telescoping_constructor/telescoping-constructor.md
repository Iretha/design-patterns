---
layout: default
title: Telescoping Constructor
parent: Creational Design Patterns
nav_order: 1099
permalink: /creational/telescoping-constructor
---

# The Telescoping Constructor (Anti?) Pattern

It's not exactly pattern, but rather a mechanism to provide default values or behavior when constructing objects. 

{: .fs-6 .fw-300 }

---

## What problems does it solve? 
If you need to provide default values or behavior when constructing objects. 

## Pros:
- To avoid code duplication
- To provide default values/ behavior

## Cons:
- It may get hard to follow
- If there are too many args, it can easily become an "anti pattern"

## How to recognize it?
When many constructors call other constructors (often with this(...)) and pass arguments.
```java
    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;

        this.registrationTime = new Date();
    }

    public Dog(String breed, int age) {
        this("Unknown name", breed, age);
    }

    public Dog(String name) {
        this(name, "Unknown breed", -1);
    }
```
## How can be improved?
When too many args, then you should switch to the Builder Pattern.

## Scenarios

* To provide default values/ behavior of your object

### Example 1

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/telescoping_constructor)

We want to implement a system, where you can register found/ lost dogs, so that owners can easily find them.
Some dogs have tags with their name, breed, age, others don't. Sometimes you can recognize their breed or age, 
sometimes not. You want always keep track of the date, when the dog is registered/ found.

1). Create the dog class
```java
import java.util.Date;

@ToString
public class Dog {
    private String name;
    private String breed;
    private int age;

    private Date registrationTime;
    
    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;

        this.registrationTime = new Date();
    }

    public Dog(String breed, int age) {
        this("Unknown name", breed, age);
    }

    public Dog(String name) {
        this(name, "Unknown breed", -1);
    }
}
```
2). Create a demo class
```java
public class _Main {

    public static void main(String[] args) {
        Dog dog1 = new Dog("Kevin", "German Shepherd", 10);
        System.out.println(dog1);

        Dog dog2 = new Dog("Pug", 2);
        System.out.println(dog2);

        Dog dog3 = new Dog("Charlie");
        System.out.println(dog3);
    }
}
```
Output:
```
Dog(name=Kevin, breed=German Shepherd, age=10, registrationTime=Wed Jul 17 10:51:28 EEST 2019)
Dog(name=Unknown name, breed=Pug, age=2, registrationTime=Wed Jul 17 10:51:28 EEST 2019)
Dog(name=Charlie, breed=Unknown breed, age=-1, registrationTime=Wed Jul 17 10:51:28 EEST 2019)
```