---
layout: default
title: Factory (Pattern?)
parent: Creational Design Patterns
nav_order: 1050
permalink: /creational/factory
---

# Factory (Pattern or not a Pattern)

People often argue about the Factory - is it a pattern or not? 
Actually it doesn't matter how you call it. I would say it is a pattern, because it provides 
"a reusable solution for a for common (repeatable) design problem", but it's not officially recognized as such.
Many call it a technique to encapsulate the instantiation process. 

The main purpose of the Factory is to encapsulate the instantiation logic and to create objects without specifying their concrete classes.
{: .fs-6 .fw-300 }

The Factory uses "creation through delegation" method and provides an interface for creating objects without specifying 
their concrete classes. In other words, this model allows us to create objects that follow a general pattern, 
whereas the client uses the general contract.
{: .fs-6 .fw-90 }

---

## What problems does it solve? 
- To hide the concrete classes/ the implementation from the client
- To hide the initialization logic from client
- The client has access only to the contract (the general interface)
- We can easily switch to another implementation

## Pros:
- Creates objects without exposing the instantiation logic to the client.
- Loose coupling - the client does't know the concrete classes
- Easy to add/ remove families

## Cons:
- To add new features (members, methods and etc.) - we have to modify the generic classes
and add implementation for each family

## How to recognize it
When you pass a parameter to a creational method and the method returns a instance of an object.
If you pass another parameter, you will get another instance (of another object).

## Scenarios
- If you want to provide some generic model of something
- If you want to "change" the implementation
- If you want to "switch" between different implementations

## Examples
### Example 1
[Source Code on Github](https://github.com/Iretha/design-patterns/tree/master/src/com/smdev/creational/factory)

Let's say are a vehicle distributor. Clients may come and order different vehicles with custom modifications.
When an order is received and a payment is done, we should contact the producer to start assembling the vehicle. 

1). Create a generic interface for a vehicle
```java
public interface Vehicle {

    String getModification();

    Vehicle assemble();
}
```
2). Create an abstract implementation of the vehicle
```java
@ToString
public abstract class AbstractVehicle implements Vehicle {

    @Getter
    private String modification;

    public AbstractVehicle(String modification) {
        this.modification = modification;
    }
}
```
3). Create enum with the types of the vehicles we distribute
```java
public enum VehicleType {
    CAR,
    TRUCK
}
```
4). Create the concrete classes 

4.1). Car
```java
@ToString
public class Car extends AbstractVehicle {

    public Car(String modification) {
        super(modification);
    }

    @Override
    public Vehicle assemble() {
        System.out.println("A car with " + getModification() + " is assembled!");
        return this;
    }
}
```
4.2). Truck
```java
@ToString
public class Truck extends AbstractVehicle {

    public Truck(String modification) {
        super(modification);
    }

    @Override
    public Vehicle assemble() {
        System.out.println("A truck with " + getModification() + " is assembled!");
        return this;
    }
}
```
5). The factory
```java

public final class VehicleFactory {
    private VehicleFactory() {
    }

    public static Vehicle getInstance(VehicleType type, String modification) {
        Vehicle vehicle = null;
        switch (type) {
            case CAR:
                vehicle = new Car(modification);
                break;
            case TRUCK:
                vehicle = new Truck(modification);
                break;
            default:
                break;
        }

        System.out.println("Vehicle created: " + vehicle);

        return vehicle;
    }
}
```
6). Demo
```java
public class _Main {

    public static void main(String[] args) {
        List<Vehicle> vehiclesToAssemble = new ArrayList<>();
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.CAR, "with manual transmission"));
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.CAR, "with auto transmission"));
        vehiclesToAssemble.add(VehicleFactory.getInstance(VehicleType.TRUCK, "all extras"));

        for (Vehicle vehicle : vehiclesToAssemble) {
            vehicle.assemble();
        }
    }
}
```
Output:
```
Vehicle created: Car()
Vehicle created: Car()
Vehicle created: Truck()
Car with with manual transmission is assembled!
Car with with auto transmission is assembled!
Track with all extras is assembled!
```
 