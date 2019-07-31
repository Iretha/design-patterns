---
layout: default
title: Vending Machine
nav_order: 8890
description: ""
permalink: /task001
---

# Design a vending machine

Design and implement a vending machine, that allows customers to buy different products with coins.

What patterns are going to use? How would you implement it?

## Short Analysis
We need a vending machine, that:
* accepts coins (one by one) and shows the value of the accepted coins
* accepts a product order, if there are accepted coins
* releases the unaccepted coin
* releases the ordered product
* returns the change (if there is)
* releases the accepted coins by user request
* has slots with products
* can be switched on/ off

## Problems
List all problems, that comes to your mind and we need to solve:
* We should provide multiple products, that can be bought
* Each product should have a price
* We should have starting balance, so that we can return change
* We should accept different coins 
* We should have a temp balance with the accepted coins, if the product is not released yet
* We should calculate the total value of the accepted coins
* We should release all unaccepted coins
* We should return change, if there is any
* We should to "calculate" how to return change (the combination of coins)
* We should know where is the chosen product (on which slot)
* We may have the same product on multiple slots
* We should add the temp balance to the total balance, when the product is released
* It should be easy to modify the product list (add or remove products)

## Design Patterns To Consider
### Singleton
* The vending machine should have only 1 instance and should control the access to the public methods in a serial way.
This is needed because only one client can be serviced at a time and this affects the balances and the product availability, 
which will be shared among all orders.

### Abstract Factory
* To identify the inserted coin, we are going to use an abstract factory, that will use the diameter and the weight.
The implementation of the abstract factory will be very simplified and it will use an enum.
We can use the COR pattern as well, but I'm not going to do it, because there is no "behavior" to be
implemented. All we need is to get the nominal of the coins and the abstract factory works for us with less code.

### Null Object
We need a default coin, that should represent any coin, that is inserted, but we are not able to recognize.

### Telescoping Constructor
To init coin holder

### Strategy 
To calculate the combination of coins when returning change


