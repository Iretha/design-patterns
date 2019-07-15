# The State Design Pattern

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/state) 

## What problems does it solve? Why to use it?
To simplify the code by separating the logic, if an object behaves differently in each of it's states.
This is done by creating different class for each state and when the state changes, we should instantiate different
concrete class that will represent the object and it's state.
It it's like transforming one object into another when something happens.

Glossary:
- Context - holds the general data (stateless data) and the current state of the object; it will be used by clients
- Abstract State - defines the general behavior; receives the context and "knows" which is the next state and how to switch to it
- Concrete state - implements the concrete behavior for the concrete state

## When to use it?
- When an object has different states and has absolutely different behavior in each of the states
- To implement a state machine - based on some input, it will switch to a different state and each state will specify the next state (again, based on the input)
- To implement a process
- To implement polymorphic behavior

## Pros:
- Single Responsibility Principle
- Simplifies the code
- To implement polymorphic behavior of an object
- Easy to extend and add new states

## Cons:
- It can be an overhead if there are only a few states
- Hard to maintain because you need different class for each object + state

## Examples from Java API
Recognizable by behavioral methods which changes its behaviour depending on the instance's state which can be controlled externally
```
javax.faces.lifecycle.LifeCycle#execute() (controlled by FacesServlet, the behaviour is dependent on current phase (state) of JSF lifecycle)
```
## Examples
It's like a butterfly metamorphosis. 
There are 4 stages from an egg to a butterfly, When changes it's state, it also changes it's behavior.

1). The egg - does not have legs, wings, can't walk, eat or fly. When the egg hatches, it becomes a larva.

2). The Larva - they grow, they does not have wings, can't fly, they molt (change their skin). When is's fully grown, they form a pupa.

3). The Pupa - in pupa (larva in something like egg) they undergo the real metamorphosis - the body parts are transformed, grow new body parts as well until they are fully formed.

4). The Butterfly - can fly etc.

Or when an egg hatches and becomes a chickling and later bird.
### Example 1 - How to implement it?
Let's say we want to implement an app for pizza delivery. Clients may use the app to order pizza for home.
We need a context, that will hold the general information about the order like - customer and delivery address and 
the current state of the order. 

1). The context - with customer info and current state
```java
@ToString
public class OrderContext {

    @Getter
    private String client;
    
    @Getter
    private String deliveryAddress;

    private OrderState state;

    public OrderContext(String client, String deliveryAddress) {
        this.client = client;
        this.deliveryAddress = deliveryAddress;

        this.state = new OrderReceived();

        System.out.println("Order Received: " + this);
    }

    public void next() {
        OrderState nextState = this.state.next(this);
        if (nextState != null) {
            this.state = nextState;
            System.out.println("Next: " + this);
        } else {
            System.out.println("Order completed!");
        }
    }
}
```
2). A State, representing the general behavior

```java
public interface OrderState {

    OrderState next(OrderContext context);
}
```
3). Abstract Implementation of the state
```java
@ToString
public abstract class AbstractState implements OrderState {

    private String name;

    public AbstractState(String name) {
        this.name = name;
    }
}
```
4). Now, we need to implement the concrete states of the order. Each concrete state "knows" which is the next state.

4.1). Order is received and pizza is ready for assembly
```java
public class OrderReceived extends AbstractState {

    public OrderReceived() {
        super("Order Confirmed!");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new ForAssembly();
    }
}
```
4.2). After a while in "for assembly" state, assembly will start and the state will change to "assembly in progress"
```java
public class ForAssembly extends AbstractState {

    public ForAssembly() {
        super("For Assembly");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new AssemblyInProgress();
    }
}
```
4.3). When the assembly is done, the pizza will be ready for baking
```java
public class AssemblyInProgress extends AbstractState {

    public AssemblyInProgress() {
        super("Assembly In Progress");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new ForBaking();
    }
}
```
4.4). When time comes, baking will start and state will change to "baking in progress"
```java
public class ForBaking extends AbstractState {

    public ForBaking() {
        super("For Baking");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new BakingInProgress();
    }
}
```
4.5). When baking is done, pizza will be ready for delivery
```java
public class BakingInProgress extends AbstractState {

    public BakingInProgress() {
        super("Baking In Progress");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new ForDelivery();
    }
}
```
4.6). When time comes, pizza will be taken out for delivery and delivery will be in progress
```java
public class ForDelivery extends AbstractState {

    public ForDelivery() {
        super("For Delivery");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new DeliveryInProgress();
    }
}
```
4.7). After the delivery is done, the state will change to "delivered"
```java
public class DeliveryInProgress extends AbstractState {

    public DeliveryInProgress() {
        super("Out For Delivery");
    }

    @Override
    public OrderState next(OrderContext context) {
        return new Delivered();
    }
}
```
4.8). The "delivered" state is the final state of the order processing
```java
public class Delivered extends AbstractState {

    public Delivered() {
        super("Delivered To Address");
    }

    @Override
    public OrderState next(OrderContext context) {
        System.out.println("Delivered to " + context.getClient() + ": address=" + context.getDeliveryAddress());
        // the final state
        return null;
    }
}
```
5). Demo class
````java
public class _Main {

    public static void main(String[] args) {
        OrderContext ctx = new OrderContext("Jon Snow", "123 Str, 23 doorbell"); // order received
        ctx.next(); // -> for assembly

        ctx.next(); // -> assembly in progress
        ctx.next(); // -> for baking
        ctx.next(); // -> baking in progress
        ctx.next(); // -> for delivery
        ctx.next(); // -> delivery in progress
        ctx.next(); // -> delivered to customer

        ctx.next(); // -> order completed
    }
}
````
Output:
```
Order Received: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=Order Confirmed!))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=For Assembly))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=Assembly In Progress))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=For Baking))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=Baking In Progress))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=For Delivery))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=Out For Delivery))
Next: OrderContext(client=Jon Snow, deliveryAddress=123 Str, 23 doorbell, state=AbstractState(name=Delivered To Address))
Delivered to Jon Snow: address=123 Str, 23 doorbell
Order completed!
```
  
