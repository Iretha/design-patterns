# GoF Design Patterns (23)

Design Patterns: Elements of Reusable Object-Oriented Software (1994) is a software engineering book describing software design patterns. 
The book was written by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides (the "Gang of Four"),[1] with a foreword by 
Grady Booch. The book is divided into two parts, with the first two chapters exploring the capabilities and pitfalls of 
object-oriented programming, and the remaining chapters describing 23 classic software design patterns. 

## [Creational Design Patterns (5)](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational)

### Abstract Factory aka "Factory of Factories"
* Designed to create families of objects without specifying their concrete classes
* The type will be determined at runtime.
[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/abstract_factory)

### Builder Pattern
* Designed to improve the readability of the code when we have to build complex objects with a lot of optional fields
* Designed to ensure that the objects is always in a consistent state
[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/builder)

### Factory Method
* Defines the interface, but lets subclasses to decide which concrete class to instantiate
* Refers to the newly created object through a common interface
* "Creation through inheritance"
[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/creational/factory_method)

## Structural Design Patterns (7)
## Behavioral Design Patterns (11)