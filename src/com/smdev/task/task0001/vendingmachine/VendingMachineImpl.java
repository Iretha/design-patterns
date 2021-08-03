package com.smdev.task.task0001.vendingmachine;

import com.smdev.task.task0001.vendingmachine.balance.Coin;
import com.smdev.task.task0001.vendingmachine.balance.CoinCollection;
import com.smdev.task.task0001.vendingmachine.balance.RefundPolicy;
import com.smdev.task.task0001.vendingmachine.security.SecuredResource;
import com.smdev.task.task0001.vendingmachine.security.SecurityException;
import com.smdev.task.task0001.vendingmachine.security.SecurityPolicy;
import com.smdev.task.task0001.vendingmachine.product.Product;
import com.smdev.task.task0001.vendingmachine.product.ProductStorage;

/**
 * We use builder to guarantee a consistent state of the vending machine
 */
public class VendingMachineImpl implements VendingMachine, VendingMachineService, SecuredResource {
    private final SecurityPolicy securityPolicy;
    private final RefundPolicy refundPolicy;

    private final CoinCollection machineBalance;
    private final CoinCollection orderBalance;
    private final ProductStorage productStorage;

    VendingMachineImpl(SecurityPolicy securityPolicy, RefundPolicy refundPolicy) {
        this.securityPolicy = securityPolicy;
        this.refundPolicy = refundPolicy;

        this.machineBalance = new CoinCollection();
        this.orderBalance = new CoinCollection();
        this.productStorage = new ProductStorage();
    }

    @Override
    public void loadCoins(Coin coin, int quantity) {
        this.machineBalance.add(coin, quantity);
        System.out.println(coin + " loaded. Current total balance is: " + this.machineBalance.getTotalAmount());
    }

    @Override
    public void printBalance() {
        System.out.println("\nPrinting balance...");
        this.machineBalance.getItems().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("Total Balance: " + this.machineBalance.getTotalAmount());
        System.out.println();
    }

    @Override
    public void withdrawCoins(Coin coin, int quantity) {
        this.machineBalance.release(coin, quantity);
        System.out.println(coin + " withdrew. Current total balance is: " + this.machineBalance.getTotalAmount());
    }

    @Override
    public void loadProducts(Product product, int quantity) {
        this.productStorage.add(product, quantity);
        System.out.println(product + " " + quantity + " loaded. ");
    }

    @Override
    public void printProducts() {
        System.out.println("\nPrinting products...");
        this.productStorage.getItems().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println();
    }

    @Override
    public void withdrawProducts(Product product, int quantity) {
        this.productStorage.release(product, quantity);
        System.out.println(product + " withdrew with quantity=" + quantity);
    }

    @Override
    public VendingMachineService accessServiceInterface(String pinCode) throws SecurityException {
        this.securityPolicy.authorize(this, pinCode);
        System.out.println("Access allowed.");
        return this;
    }

    @Override
    public void insertCoin(Coin coin) {
        this.orderBalance.add(coin, 1);
        System.out.println(coin + " inserted. New balance is " + this.orderBalance.getTotalAmount());
    }

    @Override
    public int getOrderBalance() {
        return this.orderBalance.getTotalAmount();
    }

    @Override
    public void orderProduct(Product product, int quantity) throws VendingMachineException {
        if (product == null) {
            throw new VendingMachineException("Please, select a product!");
        }

        boolean isProductAvailable = this.productStorage.hasQuantity(product, quantity);
        if (!isProductAvailable) {
            throw new VendingMachineException("Not enough quantity of " + product);
        }

        int refundAmount = calculateRefundAmount(product.getPrice(), quantity);
        if (refundAmount < 0) {
            throw new VendingMachineException("Not enough money to complete the purchase. Please, insert more or terminate order.");
        }

        CoinCollection refund = this.refundPolicy.calculateRefund(refundAmount, this.machineBalance, this.orderBalance);
        if (refundAmount > 0 && refund.isEmpty()) {
            throw new VendingMachineException("Not enough coins in the VM to refund.");
        }

        this.machineBalance.acquireCollection(this.orderBalance);
        this.machineBalance.releaseCollection(refund);
        this.productStorage.release(product, quantity);
    }

    private int calculateRefundAmount(int price, int quantity) {
        return this.orderBalance.getTotalAmount() - (price * quantity);
    }

    @Override
    public void terminate() {
        System.out.println("\nClient wants to cancel the order.");
        this.orderBalance.releaseAll();
        System.out.println("Order balance released. Operation terminated.");
        System.out.println("\nCurrent order balance is: " + this.orderBalance.getTotalAmount());
    }
}
