---
layout: default
title: Flyweight (GoF)
parent: Structural Design Patterns
nav_order: 2050
permalink: /structural/flyweight
---

# The Flyweight Design Pattern

The Flyweight Pattern is designed to share objects for better efficiency or consistency.
{: .fs-6 .fw-300 }

---

## What problems does it solve?
It solves memory issues and lowers the needed memory. 
You can optimize the memory and lower RAM usage by dividing the objects into two parts:
- shareable (intrinsic) state 
- non-shareable (extrinsic) state.
Then objects with common data can use the same shared state, instead of replicating the common data for each object.

Glossary:
- Flyweight - holds/ represents the shareable (intrinsic/ immutable) state of an object
- FlyweightFactory - holds all Flyweight objects (like cache) and "creates" new Flyweight object (if not available in cache or returns the available)
- Concrete object - holds/ represents the non-shareable (extrinsic/ mutable) state. This is the state that changes over time or is unique for the object
- Client - the one, who uses the objects with their shareable and non-shareable states

## Pros:
- Lowers the memory usage of your application by decreasing duplicated data (even object instances)

## Cons:
- Increases the complexity of the code
- There is an overhead if you need to recalculate/recreate/sync the shareable (intrinsic) state of the object

## How to recognize it?
When you call creational method, that returns a cached instance instead of a new one.
```java
public final class CountryFactory {

    private static Map<String, Country> cache = new HashMap<>();

    private CountryFactory() {
        // hidden
    }

    public static Country createCountry(String name) {
        if (!cache.containsKey(name)) {
            cache.put(name, new Country(name));
        }
        return cache.get(name);
    }
}
```
## Examples from Java API
```
java.lang.Integer#valueOf(int) (also on Boolean, Byte, Character, Short, Long and BigDecimal)
```
## Scenarios
* When you have common data that is shared between multiple objects
* When the application creates large number of similar objects (too many instances)
* When you need to reduce the storage cost of the application

### Example 1
Let's say we have a register of landmarks. We want to browse landmarks by country. As there are many landmarks in a single country,
we can say that multiple landmarks "share the same country". This means that the class Country can be a Flyweight object 
and class "Landmark" is our unique object, that shares common data (same country) with other unique objects.

[Source Code](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/flyweight) 

1). Create class Country
```java
public class Country {

    private String name;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString().concat("(" + this.name + ")");
    }
}
```
2). Create Factory, which will create new country if necessary or return an existing instance
```java
public final class CountryFactory {

    private static Map<String, Country> cache = new HashMap<>();

    private CountryFactory() {
        // hidden
    }

    public static Country createCountry(String name) {
        if (!cache.containsKey(name)) {
            cache.put(name, new Country(name));
        }
        return cache.get(name);
    }
}
```
3). Create a class Landmark (The unique data)
```java
@ToString
public class Landmark {

    @Getter
    private Country country;

    private String landmarkName;

    private String landmarkDescription;

    public Landmark(String countryName, String landmarkName, String landmarkDescription) {
        this.country = CountryFactory.createCountry(countryName);
        this.landmarkName = landmarkName;
        this.landmarkDescription = landmarkDescription;
    }
}
```
4). Create a demo class
```java
public class _Main {

    public static void main(String[] args) {

        List<Landmark> landmarks = new ArrayList<>();

        landmarks.add(new Landmark("Italy", "St Peter's Basilica, Vatican City", "It remains one of the two largest churches in the world."));
        landmarks.add(new Landmark("Italy", "Milan Cathedral (Duomo) – Milan, Italy", "It is the largest Gothic cathedral and the second largest Catholic cathedral in the world."));
        landmarks.add(new Landmark("Cambodia", "Siem Reap", "The majestic structure is Cambodia's most beloved and best-preserved temple."));
        landmarks.add(new Landmark("Peru", "Machu Picchu", "Located 8,000 ft high in the Andes, Peru's famous lost city is one of the most famous and spectacular ruins in the world."));
        landmarks.add(new Landmark("India", "Taj Mahal – Angra", "Standing majestically on the banks of the River Yamuna, India's national treasure is a symbol of love and romance. "));

        for (Landmark landmark : landmarks) {
            System.out.println(landmark);
        }
    }
}
```
Output:
```
Landmark(country=Country@e580929(Italy), landmarkName=St Peter's Basilica, Vatican City, landmarkDescription=It remains one of the two largest churches in the world.)
Landmark(country=Country@e580929(Italy), landmarkName=Milan Cathedral (Duomo) – Milan, Italy, landmarkDescription=It is the largest Gothic cathedral and the second largest Catholic cathedral in the world.)
Landmark(country=Country@1cd072a9(Cambodia), landmarkName=Siem Reap, landmarkDescription=The majestic structure is Cambodia's most beloved and best-preserved temple.)
Landmark(country=Country@7c75222b(Peru), landmarkName=Machu Picchu, landmarkDescription=Located 8,000 ft high in the Andes, Peru's famous lost city is one of the most famous and spectacular ruins in the world.)
Landmark(country=Country@4c203ea1(India), landmarkName=Taj Mahal – Angra, landmarkDescription=Standing majestically on the banks of the River Yamuna, India's national treasure is a symbol of love and romance. )

```
As you can see, landmarks in Italy share the same country instance "Country@e580929(Italy)"
