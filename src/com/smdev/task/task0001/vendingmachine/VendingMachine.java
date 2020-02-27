package com.smdev.task.task0001.vendingmachine;


import com.smdev.task.task0001.vendingmachine.balance.Coin;
import com.smdev.task.task0001.vendingmachine.security.SecurityException;
import com.smdev.task.task0001.vendingmachine.product.Product;

/**
 * Public API for the Vending Machine
 */
public interface VendingMachine {

    void insertCoin(Coin c);

    int getOrderBalance();

    void orderProduct(Product product, int quantity) throws VendingMachineException;

    void terminate();

    VendingMachineService accessServiceInterface(String pinCode) throws SecurityException;
}
