# Behavioral Design Patterns (11)

### Chain Of Responsibility
* Decouples Sender & Receiver
* Chain can be "modified" at runtime by chnaging the members
* Does not guarantee that the request will be handled

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/chain_of_responsibility)

### Command
* When you need to decouple the Invoker and the Receiver and let them "talk" via commands
* The only handler is the Receiver (the only one, who knows hot to perform the commands)

[Detailed Explanation & Examples](https://github.com/Iretha/ebook-design-patterns/blob/master/src/com/smdev/gof/behavioral/command)

