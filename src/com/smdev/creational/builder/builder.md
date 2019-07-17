---
layout: default
title: Builder (GoF)
parent: Creational Design Patterns
nav_order: 1010
permalink: /creational/builder
---

# The Builder Design Pattern

Designed to build complex objects with a lot of optional fields.
{: .fs-6 .fw-300 }

---

## What problems does it solve? 
To write readable, maintainable, clean and understandable code when you have to setup complex objects with a lot of fields. 
Very useful when you need to implement an immutable class or if you need to ensure that the instance is in consistent state.

## When to use it?
- If there are too many constructor arguments
- If you can have many different combinations of optional arguments
- If you want to pass the optional arguments in any order
- If you don't have all the values when the object is instantiated
- If you need to construct immutable class with a large number of arguments
- If you want to guarantee a consistent state of the objects, because all required fields are in the constructor
- If you want to have only 1 state of the object

## Pros:
- Improves usability and readability when constructing complex objects
- Ensures only one state of the object (because everything will be set at once, not property by property) - 
this means that the data will be always in a consistent state
- Perfect solution for implementing an immutable class that has large number of arguments

## Cons:
- Double the number of lines of code for each attribute and for setting the values
- Harder to extend - when you have to add new field, it requires more code to do it

## How to recognize it?
When you need to call a creational method, that returns the instance itself and you can chain the calls.

## How can be improved?
- Make the constructors private - This will ensure that the objects will be build as you expect 
and no one will use the constructors directly. 
- Do not add setters to the "Car" class, so that no one can set the values directly

## Examples from Java API
```
java.lang.StringBuilder#append() (unsynchronized)
java.lang.StringBuffer#append() (synchronized)
java.nio.ByteBuffer#put() (also on CharBuffer, ShortBuffer, IntBuffer, LongBuffer, FloatBuffer and DoubleBuffer)
javax.swing.GroupLayout.Group#addComponent()
All implementations of java.lang.Appendable
java.util.stream.Stream.Builder
Quartz Scheduler uses JobBuilder and TriggerBuilder
```
### Java 8:
#### Calendar 
```
Calendar cal = new Calendar.Builder()
    .setCalendarType("iso8601").setWeekDate(2013, 1, MONDAY).build();
    (since java 8)
```
#### Stream.Builder
#### IntStream.Builder
#### LongStream.Builder
#### DoubleStream.Builder

### Java 7:
#### Locale
```
Locale aLocale = new Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build();
```
### !!! Important
- StringBuilder is close to, but does NOT implement a Builder Pattern - it's instantiated with new and 
does not serve the same purpose "to construct complex objects".
```
StringBuilder b = new StringBuilder();
```
## Scenarios
* If an object might be instantiated/ constructed in many different ways. 

* If you have many optional parameters i.e. if you have some search criteria and you don't know what combination of fields 
will be used by the client to do the search.

* If you need to configure different jobs to run at specific day of the year/ at specific day of the week/ on a specific date 
or combination of them. 

* If the result should be immutable

* If the result should be in a consistent state

* If you start with a factory, but than you realize that there are to many permutations (combinations of constructor args)

* If you need to implement an app for pizza delivery. There should be always dough (consistent state), 
but you can always make your own combination of the other products or they depend on the recipe for the concrete pizza you ordered.

* If you have too many constructors with different args and they get hard to read or args constantly increase (to avoid the so called
Telescoping Constructor Pattern)

### Example 1

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/builder) - Full example, with the best practises applied to it

1). Create your class i.e. "Car"
```java
public class Car {
    
}
```
2). Add the class fields, they should be "private" i.e. "vin", "brand", "model", "modelYear"
```java
public class Car {

    private String vin;
    private String brand;
    private String model;
    private int modelYear;
    
}
```
3). Create a builder class i.e. "CarBuilder". It should be static & nested inside the "Car" class.
Naming convension is to append "Builder" to the object it builds. In our case "Car" -> "CarBuilder".
```java
public class Car {


    static class CarBuilder {
        
    }
}
```
4). Add the same fields to the Builder class
```java
public class Car {
    private String vin;
    private String brand;
    private String model;
    private int modelYear;

    static class CarBuilder {
        private String vin;
        private String brand;
        private String model;
        private int modelYear;
    }
}
```
5). Create constructor of the builder class only with the required fields (do not include your optional fields) - in our case "vin"
```java
    static class CarBuilder {
        private String vin;
        private String brand;
        private String model;
        private int modelYear;

        private CarBuilder(String vin) {
            this.vin = vin;
        }
}
```
6). Add static builder method to the Car class, that returns new instance of the builder class. It should accept the required fields as method arguments.
```
    public static CarBuilder builder(String vin) {
        return new CarBuilder(vin);
    }
```
7). Add methods for your optional fields to the Builder class. 
Each method should return the builder itself, so that you can chain them in any order.
```
        public CarBuilder brand(String brand){
            this.brand = brand;
            return this;
        }

        public CarBuilder model(String model){
            this.model = model;
            return this;
        }

        public CarBuilder modelYear(int modelYear){
            this.modelYear = modelYear;
            return this;
        }
```
8). Add constructor to Car class, that takes all the fields
```
    private Car(String vin, String brand, String model, int modelYear) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
    }
```
9). Add build method to the builder class, that will return the instance of the object with all fields
```
        public Car build(){
            return new Car(this.vin, this.brand, this.model, this.modelYear);
        }
```
10). Generate getters in you Car class in order to access field values

### Example 2 - Builder with Lombok

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/creational/builder) - Lombok implementation of the Builder Pattern (very easy to use)

1). Create the class
2). Add class fields
3). Add builder method - only if you have some required fields
4). Add annotations:
4.1). @Builder - Lombok will create a Builder for you. Change the name of the builder method to "hiddenBuilder" with "builderMethodName".
We need this, because we have some required arguments that we want to pass, when creating Car object.
4.2). @ToString - Lombok will override toString with all fields
4.3). @Getter - Lombok will create Getter-s


```java
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(builderMethodName = "hiddenBuilder")
public class Car {

    private String vin; // vehicle identification number is a required  field
    private String brand; // optional
    private String model; // optional
    private Integer modelYear; // optional

    /**
     * Necessary only if you have some required fields
     * @param vin
     * @return
     */
    public static Car.CarBuilder builder(String vin) {
        return hiddenBuilder().vin(vin);
    }
}
```

