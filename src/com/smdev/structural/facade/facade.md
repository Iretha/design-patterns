---
layout: default
title: Facade
parent: Structural Design Patterns
nav_order: 2040
permalink: /structural/facade
---

# The Facade Design Pattern

GoF Design Patterns -> Structural Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/structural/facade) 

## What problems does it solve? Why to use it?

Provides simple facade for the client and hides the complexity of the implementation just like the "face of 
a building". People on the street can see only outside, they do not know how the building was built, 
what materials are used etc.

JDBC driver uses facade to hide complexity of creating connection to different database servers.

## When to use it?

- To hide complexity
- To divide a system into multiple subsystems, that doesn't know much about the complexity of the subsystems they iterract with.

## Pros:
- Hides complexity
- Provides high level of isolation
- Reduces dependencies between components/ subsystems

## Cons:
- Larger API
- May hide important information as useless information

## Examples from Java API
Recognizable by behavioral methods which internally use instances of different independent abstract/interface types
```
JDBC driver uses facade to hide complexity of getting connection to different database servers (Oracle, MySql, etc)
javax.faces.context.FacesContext, it internally uses among others the abstract/interface types LifeCycle, ViewHandler, NavigationHandler 
and many more without that the enduser has to worry about it (which are however overrideable by injection).
javax.faces.context.ExternalContext, which internally uses ServletContext, HttpSession, HttpServletRequest, HttpServletResponse, etc.
```
## Examples

### Example 1
We (as customers) want a single pay desk service or mobile app, where we can pay all our bills no matter which provider we use.
In this case we need a Payment Service Facade, that we can use to pay our bills. The facade will introduce very simple interface. 
What's hidden inside is, that each provider has it's own payment service, where we should submit each payment.

1). Create the Facade - we will accept payments for four different providers
```java
public interface PaymentServicesFacade {

    void payPhoneBill(String billNo);

    void payElectricityBill(String billNo);

    void payGasBillToProvider1(String billNo);

    void payGasBillToProvider2(String billNo);
}
```
2). Create separate interface for each provider
```java
public class ElectricityPaymentSystem {

    public void pay(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the electricity provider.");
    }
}
```

```java
public class GasOnlinePaymentServices {

    public void payBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the gas provider 1.");
    }
}
```

```java
public class SuperGasPaymentProvider {

    public void payBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the gas provider 2.");
    }
}
```
```java
public class TelecommOnlinePayDesk {

    public void payPhoneBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the mobile services provider.");
    }
}
```
3). Now we want to implement the facade
```java
public class PaymentServicesFacadeImpl implements PaymentServicesFacade {

    @Getter
    private Customer customer;

    public PaymentServicesFacadeImpl(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void payPhoneBill(String bill) {
        TelecommOnlinePayDesk onlinePayDesk = new TelecommOnlinePayDesk();
        onlinePayDesk.payPhoneBill(getCustomer(), bill);
    }

    @Override
    public void payElectricityBill(String bill) {
        ElectricityPaymentSystem paymentSystem = new ElectricityPaymentSystem();
        paymentSystem.pay(getCustomer(), bill);
    }

    @Override
    public void payGasBillToProvider1(String bill) {
        GasOnlinePaymentServices paymentServices = new GasOnlinePaymentServices();
        paymentServices.payBill(getCustomer(), bill);
    }

    @Override
    public void payGasBillToProvider2(String bill) {
        SuperGasPaymentProvider paymentServices = new SuperGasPaymentProvider();
        paymentServices.payBill(getCustomer(), bill);
    }
}
```

4). Run the demo
```java
public class _Main {

    public static void main(String[] args) {
        // Jon Snow
        PaymentServicesFacade paymentServices1 = new PaymentServicesFacadeImpl(new Customer("Jon Snow"));
        paymentServices1.payElectricityBill("BN123/20");
        paymentServices1.payGasBillToProvider2("233834esd4rcWQWFG3");
        paymentServices1.payElectricityBill("122398");
        
        System.out.println("");

        // Alice Young
        PaymentServicesFacade paymentServices2 = new PaymentServicesFacadeImpl(new Customer("Alice Young"));
        paymentServices2.payGasBillToProvider1("ACVVVS");
    }
}
```
Output:
```
Payment for billNo= BN123/20 from Jon Snow received by the electricity provider.
Payment for billNo= 233834esd4rcWQWFG3 from Jon Snow received by the gas provider 2.
Payment for billNo= 122398 from Jon Snow received by the electricity provider.

Payment for billNo= ACVVVS from Alice Young received by the gas provider 1.
```


