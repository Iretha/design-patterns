package com.smdev.task.task0001.vending_machine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum VendingMachine {
    INSTANCE;

    private MoneyManager moneyManager;
    private ProductStorage productManager;

    VendingMachine() {
        init();
    }

    private void init() {
        List<ProductSlot> productSlots = Arrays.asList(
                new ProductSlot(Product.CROISSANT, 15),
                new ProductSlot(Product.CROISSANT, 8),
                new ProductSlot(Product.MUFFIN, 20),
                new ProductSlot(Product.PIZZA, 10),
                new ProductSlot(Product.PIZZA, 9)
        );
        this.productManager = new ProductStorage(productSlots);

        Map<Coin, Integer> initialBalance = Map.of(
                Coin.CENTS_10, 10,
                Coin.CENTS_25, 10,
                Coin.CENTS_50, 10,
                Coin.DOLLAR_1, 10
        );
        this.moneyManager = new MoneyManager(initialBalance);
    }

    public synchronized void insertCoin(double diameter, double weight) {
        Coin coin = Coin.get(diameter, weight);
        if (Coin.UNKNOWN.equals(coin)) {
            System.out.println("Coin " + coin.getLabel() + " NOT accepted.");
        } else {
            System.out.println("Coin " + coin.getLabel() + " accepted.");

            this.moneyManager.addToCustomerBalance(coin);
        }
        System.out.println("Customer Balance=" + this.moneyManager.getCustomerAmount());
    }

    public synchronized void cancelPurchase() {
        this.moneyManager.releaseCustomerBalance();
    }

    public synchronized void purchaseProduct(int buttonNo) {
        Product product = Product.getByButton(buttonNo);
        if (product == null) {
            System.err.println("No product at the selected position!");
            return;
        }

        if (!this.productManager.isProductAvailable(product)) {
            System.err.println("Product not available!");
            return;
        }

        try {
            this.moneyManager.acquireFromCustomerBalance(product.getPrice());
            this.productManager.releaseProduct(product);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
