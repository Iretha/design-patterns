# GoF Design Patterns Cheat List

## GoF Creational Design Patterns (5)

### Abstract Factory or Factory of Factories
- The system consists of multiple families of objects, and these families are designed to be used together, but
the client doesn't know what the concrete classes are.

I.e. we want to build some user interface, which consists of a text field and a button.
We may run our application in android mode or in swift mode, which will be determined at runtime.
Families get instantiated depending on the chosen platform.

### Builder
- If you want to have only 1 state of the object
- If you can have many different combinations of optional arguments 
- If you need to construct immutable class with a large number of arguments
- Required fields can be passed with the constructor to ensure consistent state

I.e. almost everything can be done with a builder. 

### Factory Method
- Factory Method is "creation through inheritance"
- The superclass specifies all standard and generic behavior, but let subclasses to decide which concrete class to instantiate and to implement the details

I.e. when you want to delegate to subclasses class to decide what to instantiate.

### Prototype
- When creating a new object is an expensive operation you can use to prototype (the existing object) and clone it (with some modifications)

I.e. creating a new edition of a book, which you have already loaded in the memory, 
with all illustrations, content and etc and you need only to increment the edition number and the publish date.

### Singleton
- When you want to have only one entry point to something and to coordinate actions across the system.

I.e. a mobile phone, that can have only one mobile application on focus at a time and if you move something on focus, 
all other apps, that are running, should be paused.

## GoF Structural Design Patterns (7)

### Adapter
- Two make two incompatible interfaces compatible.
- Adapters only make them compatible, they do not add functionality.

I.e. to adapt air conditioners, that do not support WI-FI, to be controlled remotely via internet. Just make an adapter,
that receives commands over internet and converts them to commands over infrared/ bluetooth.

### Bridge
- Prefer composition over inheritance
- If you have two different hierarchies that should work together - extend one of them (inherit) 
and hold the other as a member ("has-a-relation") = The Bridge Pattern.

I.e. You produce cars and buses with manual transmission and automatic transmission. 
Instead of creating 4 subclasses in a single hierarchy, you may separate them in two different an later, if you start
producing trucks, you will add only one class, instead of two more classes.

### Composite
- The pattern could be used to implement a tree-like, hierarchical structure of elements, that have same behavior. 

I.e. Often the Composite Design Pattern is illustrated with a FileSystem, which has Files and Folders.
Folders inherit the behavior of the files (like - delete, copy, move), but also have children, which
might be files and/or other folders, containing files and other folders and etc.

### Decorator
- When objects may support many different features, but you want to make combinations of them at runtime.
- The additional behavior can be added or removed at runtime.

I.e. When you are ordering pizza, you may want to choose what to have on it (cheese, paprika, tomato souse, garlic souse etc.).
You may make any combination you want. Instead of having so many concrete classes for every possible combination, 
you can implement the Decorator pattern and enable the features at runtime, based on the user input.

### Facade
- Provides simple interface for client and hides complexity
- Reduces dependencies if used for modules/ subsystems

I.e. JDBC driver uses facade to hide complexity of creating connection to different database servers.

### Flyweight
- Lowers the memory usage of your application by decreasing duplicated data (even object instances)
- Used for caching

I.e. When you have some immutable objects that can be reused by other objects or if some objects share common data

### Proxy
* To control or manage access to the protected object
* To cache or log requests/ responses

I.e. When you want to protect or hide something "behind a wall".

## GoF Behavioral Design Patterns (11)

### Chain of Responsibility
* Decouples Sender & Receiver
* Chain can be "modified" at runtime by changing the members
* Does not guarantee that the request will be handled

I.e. When you have multiple handlers and they do not have specific priority.

### Command
* When you need to decouple the Invoker and the Receiver and let them "talk" via commands
* The only handler is the Receiver (the only one, who knows hot to perform the commands)

I.e. When you have a Remote Control (the invoker) that controls a TV (the receiver = the only handler)

### Iterator
* When you need to traverse some custom collection of elements sequentially, based on some business rule
* If you need more complex way to traverse elements, than the provided inner iterators

I.e when you need to traverse only the elements that have some specific value, assigned to a property. If you
have a collection of books and you want to traverse only the books, that belong to the Mystery genre.