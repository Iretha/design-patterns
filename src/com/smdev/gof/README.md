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

### Interpreter
* To build a language with a simple grammar (simple search engines)
* To produce different outputs of the same thing

I.e. when you have some domain and you want to create a domain language to support simple searches like "find dogs where breed eq german_shepherd and gender eq female"

### Iterator
* When you need to traverse some custom collection of elements sequentially, based on some business rule
* If you need more complex way to traverse elements, than the provided inner iterators

I.e when you need to traverse only the elements that have some specific value, assigned to a property. If you
have a collection of books and you want to traverse only the books, that belong to the Mystery genre.

### Mediator
* If you need to refactor some tight coupled components, that interact together
* If you need to centralize the communication between components

I.e when a change in one component, leads to changes/ updates in other components.

### Memento
* If you need to restore previous state of an object

I.e you have a version control system that should be capable of restoring the version to some specific commit

### Observer / Subscribe-Publish 
* If you need to notify multiple observers (subscribers), if something changes/ happens

I.e If you own a media channel and people can subscribe to topics and receive news or updates on the topics

### State
* When an object has different states and has absolutely different behavior in each of the states (like metamorphosis or transformation)
* When objects transforms into another object, when something changes/ happens 

I.e it's more like a transformation (an object transforms into another object) or like when an egg hatches and becomes a bird

### Strategy
* If you have many classes with similar behavior, you can extract the "behaviour"/ algorithm in separate class and reuse it
* If you want to "switch" the behavior
* If you have an objects that can "act" differently

I.e. you want to create an universal chat app, that can send messages via different service providers: sms, email, facebook etc., 
you can easy create different send strategies and later you can easy add or remove strategies.

### Template Method
* When multiple classes have similar behavior with slight differences
* When multiple classes share almost same algorithm
* When refactoring classes with duplicated code

I.e. when classes have very similar behavior

### Visitor
* To separate the algorithm from the object

I.e when you don't want to change the existing code to implement the algorithm withing the class, but rather you want it in a different class