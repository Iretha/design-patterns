# Behavioral Design Patterns (11)

### Chain Of Responsibility
* Decouples Sender & Receiver
* Chain can be "modified" at runtime by changing the members
* Does not guarantee that the request will be handled

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/chain_of_responsibility)

### Command
* When you need to decouple the Invoker and the Receiver and let them "talk" via commands
* The only handler is the Receiver (the only one, who knows hot to perform the commands)

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/command)

### Interpreter
* To build a language with a simple grammar (simple search engines)
* To produce different outputs of the same thing

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/interpreter)

### Iterator
* When you need to traverse some custom collection of elements sequentially, based on specific business rule
* If you need more complex way to traverse elements, than the provided inner iterators

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/iterator)

### Mediator
* If you need to refactor some tight coupled components, that interact together
* If you need to centralize the communication between components

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/mediator)

### Memento
* If you need to restore the state of an objects

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/memento)

### Observer / Subscribe-Publish 
* If you need to notify multiple observers, when something changes

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/observer)

### State
* When an object has different states and has absolutely different behavior in each of the states (like metamorphosis or transformation)
* When objects transforms into another object, when something changes/ happens (like when an egg hatches and becomes a bird)

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/state)

### Strategy
* If you have many classes with similar behavior, you can extract the "behaviour"/ algorithm in separate class and reuse it
* If you want to "switch" the behavior
* If you have an objects that can "act" differently

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/strategy)
