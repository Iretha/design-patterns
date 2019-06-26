# Structural Design Patterns (5)

### Adapter
* The adapter pattern makes two incompatible interfaces compatible without changing their existing code.
* Adapters do not extend functionality, they just make two things compatible.

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/structural/adapter)

### Bridge
* If your class does things that can be done independently and doesn't actually care how they work and 
how they are implemented, then you MUST move them out of this class, maybe to separate hierarchy 
and use the functionality you need referring the abstraction (interface), not the implementation (classes) itself
* Applies "composition over inheritance" concept
* Decouples abstraction from it's implementation, so that the two can live independently
* Reduces the number of sub classes

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/structural/bridge)

