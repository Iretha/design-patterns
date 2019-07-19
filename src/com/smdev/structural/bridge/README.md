---
layout: default
title: Bridge (GoF)
parent: Structural Design Patterns
nav_order: 2010
permalink: /structural/bridge
---

# The Bridge Design Pattern

The Bridge Pattern is designed to separate object abstraction from the implementation, 
so that you can change implementation without affecting the rest of the code.
{: .fs-6 .fw-300 }

---

The Bridge Pattern is also known as Handle/ Body.

The Bridge Design Pattern is useful when you have two different hierarchies. The Bridge approach 
is to extend one of them (inherit) and to hold the other as a member ("has-a-relation").

## What problems does it solve?
If your class does things that can be done independently and doesn't actually care how they work and 
how they are implemented, then you MUST move them out of this class, maybe to a separate hierarchy 
and use the functionality you need, referring the abstraction (interface), not the implementation (classes) itself.

Concepts:
- Prefer composition over inheritance
- Abstraction contains implementation interface as a member (through composition), which reduces the level of inheritance

## Pros:
- Decouple abstraction from it's implementation, so that the two can live independently
- Abstraction and Implementation can change independent each other and they are not bound at compile time
- Reduction in the number of sub classes (level of inheritance)
- Improved Extensibility
- Loosely coupled
- Easy to test and mock
- Can be developed independently
- To share abstraction or implementation between multiple projects
- To change implementation at runtime

## Cons:
- Increased complexity due to over use of composition (HAS-A relation)
- Many java interfaces with a single implementation 

## How to recognize it?
- When there are two different hierarchies that interact together and if you call a method, 
that method is actually implemented by the other hierarchy.

```java
public class Car extends Vehicle {

    public Car(Gear gear) {
        super(gear);
    }
}

public class _Main {

    public static void main(String[] args) {
        Vehicle car1 = new Car(new ManualGear());
        car1.changeGear();

        Vehicle car2 = new Car(new AutomaticGear());
        car2.changeGear();

        Vehicle bus1 = new Bus(new AutomaticGear());
        bus1.changeGear();
    }
}
```
## Scenarios
* Instead of maintaining multiple very large classes, that support both, you can separate them in two different hierarchies
* When you want to extend more than one class (impossible, but you want to do it), then you may need to apply the bridge pattern
* If you need to duplicate some method in another class, because you can not extend it - it's time to apply the bridge pattern  
* When you need to multiply the concrete classes, because there is something else you want to reuse -> MOVE it out!
* When you need to switch to another implementation without affecting the client

### Example 1

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/bridge) 

Let's assume that we represent a vehicle factory.
We produce cars and buses. Both can be ordered with a manual or an automatic gear.

If we don't use any pattern, we would need the following classes - CarWithManualGear, CarWithAutomaticGear, BusWithManualGear and BusWithAutomaticGear.
When implementing the classes, we would duplicate some functionality in order to handle the gear.

In case we decide to apply the Bridge Pattern, we will separate them in two different hierarchies - gear and vehicle:

1). The "gear" package:

1.1). Gear Interface
```java
public interface Gear {

    void change();
}
```
1.2). Manual Gear
```java
public class ManualGear implements Gear {

    @Override
    public void change() {
        // do something manual
    }

    @Override
    public String toString() {
        return "Manual Transmission";
    }
}
```
1.3). Automatic Gear
```java
public class AutomaticGear implements Gear {

    @Override
    public void change() {
        // do something automatically
    }

    @Override
    public String toString() {
        return "Automatic Transmission";
    }
}
```
2). The "vehicle" package:

2.1). Generic Vehicle
```java
public class Vehicle {

    private Gear gear;

    public Vehicle(Gear gear) {
        this.gear = gear;

        if (this.gear != null) {
            System.out.println("New " + getClass().getSimpleName() + " with " + gear + " created!");
        } else {
            System.out.println("New " + getClass().getSimpleName() + " with no gear created!");
        }
    }

    public void changeGear() {
        if (this.gear != null) {
            this.gear.change();

            System.out.println("Changing gear - " + this.gear);
        } else {
            System.out.println("No gear to available!");
        }
    }
}
```
2.2). Car
```java
public class Car extends Vehicle {

    public Car(Gear gear) {
        super(gear);
    }
}
```
2.3). Bus
```java
public class Bus extends Vehicle {

    public Bus(Gear gear) {
        super(gear);
    }
}
```
3). Example
```java
public class _Main {

    public static void main(String[] args) {
        Vehicle car1 = new Car(new ManualGear());
        car1.changeGear();

        Vehicle car2 = new Car(new AutomaticGear());
        car2.changeGear();

        Vehicle bus1 = new Bus(new AutomaticGear());
        bus1.changeGear();
    }
}
```
Output:
```ssh
New Car with Manual Transmission created!
Changing gear - Manual Transmission

New Car with Automatic Transmission created!
Changing gear - Automatic Transmission

New Bus with Automatic Transmission created!
Changing gear - Automatic Transmission
```
