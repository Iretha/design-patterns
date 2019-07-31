package com.smdev.task.task0001.vending_machine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum VendingMachine {
    INSTANCE;

    private Cashier cashier;
    private Storage storage;

    VendingMachine() {
        init();
    }

    private void init() {
        Map<CoinType, Integer> initialBalance = Map.of(
                CoinType.CENTS_10, 10,
                CoinType.CENTS_25, 10,
                CoinType.CENTS_50, 10,
                CoinType.DOLLAR_1, 10
        );
        this.cashier = new Cashier(initialBalance);

        List<StorageSlot> slotsWithProducts = Arrays.asList(
                new StorageSlot(ProductType.CROISSANT, 15),
                new StorageSlot(ProductType.CROISSANT, 8),
                new StorageSlot(ProductType.MUFFIN, 20),
                new StorageSlot(ProductType.PIZZA, 10),
                new StorageSlot(ProductType.PIZZA, 9)
        );
        this.storage = new Storage(slotsWithProducts);
    }

    public synchronized void insertCoin(double diameter, double weight) {
        CoinType coin = CoinType.get(diameter, weight);
        insertCoin(coin);
    }

    public synchronized void insertCoin(CoinType coin) {
        if (CoinType.UNKNOWN.equals(coin)) {
            System.out.println("Coin " + coin.getLabel() + " NOT accepted.");
        } else {
            System.out.println("Coin " + coin.getLabel() + " accepted.");

            this.cashier.addToCustomerBalance(coin);
        }
        System.out.println("Customer Balance=" + this.cashier.getCustomerAmount());
    }

    public synchronized void cancelPurchase() {
        this.cashier.releaseCustomerBalance();
    }

    public synchronized void purchaseProduct(int buttonNo) {
        ProductType product = ProductType.getByButton(buttonNo);
        if (product == null) {
            System.err.println("No product at the selected position!");
            return;
        }

        if (!this.storage.isProductAvailable(product)) {
            System.err.println("Product not available!");
            return;
        }

        try {
            this.cashier.acquireFromCustomerBalance(product.getPrice());
            this.storage.releaseProduct(product);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
