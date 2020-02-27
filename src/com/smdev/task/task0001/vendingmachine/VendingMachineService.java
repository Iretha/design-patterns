package com.smdev.task.task0001.vendingmachine;


import com.smdev.task.task0001.vendingmachine.balance.Coin;
import com.smdev.task.task0001.vendingmachine.product.Product;

/**
 * Public API for the Vending Machine Service
 */
public interface VendingMachineService {

    void loadCoins(Coin coin, int quantity);

    void withdrawCoins(Coin coin, int quantity);

    void loadProducts(Product product, int quantity);

    void withdrawProducts(Product product, int quantity);

    void printBalance();

    void printProducts();
}
