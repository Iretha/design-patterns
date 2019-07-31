package com.smdev.task.task0001.vending_machine;

public class _Demo {

    public static void main(String[] args) {
        VendingMachine vending = VendingMachine.INSTANCE;
        vending.cancelPurchase(); // we try to trick it

        vending.insertCoin(1.35, 2.268);
        vending.cancelPurchase(); // we want our money back

        vending.insertCoin(2.13, 11.33);
        vending.insertCoin(2.15, 11.34);
        vending.insertCoin(2.15, 11.34);

        vending.purchaseProduct(100);
        vending.purchaseProduct(1);

        vending.insertCoin(2.15, 11.34);
        vending.insertCoin(2.58, 22.68);
        vending.insertCoin(1.75, 5.67);
        vending.insertCoin(1.75, 5.67);
        vending.insertCoin(1.35, 2.268);
        vending.insertCoin(1.35, 2.268);
        vending.purchaseProduct(2);

        vending.insertCoin(1.35, 2.268);
        vending.insertCoin(1.35, 2.268);
        vending.purchaseProduct(3);
    }
}
