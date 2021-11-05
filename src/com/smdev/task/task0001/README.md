---
layout: default
title: Vending Machine
nav_order: 8890
description: ""
parent: Tasks
permalink: /task001
---

# Design a vending machine

Design  a vending machine, that allows customers to buy different products with coins.

What patterns are going to use? How would you implement it?

## Short Analysis
We need a vending machine, that:
* accepts coins (one by one) and shows the value of the accepted coins
* accepts a product order, if there are accepted coins
* releases the unaccepted coin
* releases the ordered product
* returns the change (if there is)
* releases the accepted coins by user request (when "Terminate" button pressed)
* provides service interface for loading coins/ products
* provides service interface for withdrawing coins/ products

## Problems
List all problems, that come to your mind, and we need to solve:
* We should provide multiple products, that can be bought
* Each product should have a price
* We should have starting balance, so that we can return change
* We should accept different coins 
* We should have a temp balance with the accepted coins, if the product is not released yet
* We should calculate the total value of the accepted coins
* We should release all unaccepted coins
* We should return change, if there is any
* We should "calculate" how to return change (the combination of coins)
* We should add the acquire the order balance, when the product is released
* It should be easy to modify the product list (add or remove products)

## Design Patterns we may want to consider
### Singleton
* You may want to limit the number of instances to 1 and ensure the serial access to the machine.

### Builder
* You may use Builder pattern to "construct"/ "initialize" the machine

### Factory
* To get an instance of the machine

### Abstract Factory
* If you want to support multiple families of products

### Strategy/ Policy 
* To calculate the combination of coins, that form the change
* To implement security policy (in order to access the machine service interface)

### Memento
You may use memento, if you want to keep the state in case the power stops ot etc.

### Command
* You may use command to "cover" to issue commands when a button is pressed
* You can combine command + memento if the power stops and you want to continue when the power is back on

[Source Code](https://github.com/Iretha/design-patterns/tree/master/src/com/smdev/task/task0001)


