---
layout: default
title: Factory Method (GoF)
parent: Creational Design Patterns
nav_order: 1020
permalink: /creational/factory-method
---

# The Factory Method Design Pattern

The Factory Method Design Pattern is designed to create objects without specifying their concrete classes.
Subclasses should decide which concrete class to instantiate.
{: .fs-6 .fw-300 }

---
The Factory Method is also known as "Virtual Constructor" Pattern.

The main differences between "Abstract Factory" and "Factory Method" is that the factory method is just a method, 
while "Abstract Factory" has a delegated (factory) class. It uses "creation through inheritance" unlike 
the abstract factory, where "creation through delegation" is used.

## What problems does it solve? 
- Defines an interface, but lets subclasses to decide which concrete class to instantiate
- Refers to the newly created object through a common interface
- The superclass specifies all standard and generic behavior, but let subclasses to implement the details
- Factory Method uses "creation through inheritance"

## When to use it?
- Client doesn't know the concrete classes that will be required at runtime, 
but just wants to get a class that will do the job.
- Factory design pattern may be used when we have a superclass with multiple sub-classes 
and based on the input, we need to return one of the sub-class.

## Pros:
- To decouple client from instantiation process
- To hide the implementation details from the client 
- Since it's just a method, it can be overridden by subclasses

## Cons:
- The code can get difficult to read and maintain as all is hidden behind an abstraction
- Can be classed as an anti-pattern, when it is not correctly used

## How to recognize it?
When you call a creational method and it returns an abstract or interface type.
```java
public abstract class Vehicle {

   public void drive() {
      VehicleDriver driver = createDriverInstance();
      driver.driveVehicle(this);
   }

   protected abstract VehicleDriver createDriverInstance();
}
```
## Examples from Java API
```
java.util.Calendar#getInstance()
java.util.ResourceBundle#getBundle()
java.text.NumberFormat#getInstance()
java.nio.charset.Charset#forName()
java.net.URLStreamHandlerFactory#createURLStreamHandler(String) (Returns singleton object per protocol)
java.util.EnumSet#of()
javax.xml.bind.JAXBContext#createMarshaller() and other similar methods
```

## Scenario

* When you don't know the object, but you just want something to do the job

### Example 1

- [Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/factory_method) 

You want to let subclasses to decide which instance of a concrete class to create.
We want to crate a generic Vehicle, that can be driven by different driver, because ot requires different skills (bus driver/ car drive etc.).
We want to let the subclass to decide which driver instance to create.

1). Create a generic vehicle (abstract class or interface) and let the subclasses to decide the concrete type of the vehicle driver.
The Vehicle class is actually the client, which knows how to do something (to drive()), but it's not interested in the concrete implementation.
```java
public abstract class Vehicle {

   public void drive() {
      VehicleDriver driver = createDriverInstance();
      driver.driveVehicle(this);
   }

   protected abstract VehicleDriver createDriverInstance();
}
```
2). Define a generic vehicle driver, who knows how to drive a vehicle
```java

public interface VehicleDriver {
    void driveVehicle(Vehicle vehicle);
}

```
3). Create classes for the concrete drivers

3.1). Car Driver
```java

public class CarDriver implements VehicleDriver {
    @Override
    public void driveVehicle(Vehicle vehicle) {
        System.out.println("I'm a car driver, driving a " + vehicle);
    }
}
```
3.2). Bus Driver
```java
public class BusDriver implements VehicleDriver {
    @Override
    public void driveVehicle(Vehicle vehicle) {
        System.out.println("I'm a bus driver, driving a " + vehicle);
    }
}
```
4). Create the concrete vehicles, which know what type of driver they need

4.1.) Car
```java
@ToString
public class Car extends Vehicle {

    @Override
    public VehicleDriver createDriverInstance() {
        return new CarDriver();
    }
}
```
4.2). Bus
```java
public class BusDriver implements VehicleDriver {
    @Override
    public void driveVehicle(Vehicle vehicle) {
        System.out.println("I'm a bus driver, driving a " + vehicle);
    }
}
```
5). Create a demo class
```java
public class _Main {

    public static void main(String[] args) {
        Vehicle vehicle1 = new Car();
        vehicle1.drive();

        Vehicle vehicle2 = new Bus();
        vehicle2.drive();
    }
}
```
Output:
```
I'm a car driver, driving a Car()
I'm a bus driver, driving a Bus()
```
