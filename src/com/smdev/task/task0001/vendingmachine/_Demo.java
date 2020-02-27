package com.smdev.task.task0001.vendingmachine;


import com.smdev.task.task0001.vendingmachine.balance.Coin;
import com.smdev.task.task0001.vendingmachine.security.SecurityException;
import com.smdev.task.task0001.vendingmachine.product.Product;

public class _Demo {

    public static void main(String[] args) {

        VendingMachine vm = VendingMachineFactory.createVendingMachine();

        VendingMachineService service = null;
        try {
            service = vm.accessServiceInterface("0000");
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {
            service = vm.accessServiceInterface("4444");
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }

        if(service != null){
            service.loadCoins(Coin.CENTS_1, 50);
            service.loadCoins(Coin.CENTS_5, 50);
            service.loadCoins(Coin.CENTS_10, 50);
            service.loadCoins(Coin.CENTS_25, 50);
            service.loadCoins(Coin.CENTS_50, 50);
            service.withdrawCoins(Coin.CENTS_50, 10);
            service.printBalance();

            service.loadProducts(Product.WATER, 12);
            service.loadProducts(Product.SODA, 12);
            service.loadProducts(Product.PEPSI, 12);
            service.loadProducts(Product.ORANGE_JUICE, 12);
            service.loadProducts(Product.ICED_TEA, 12);
            service.withdrawProducts(Product.ICED_TEA, 1);
            service.printProducts();
        }

        try {
            vm.orderProduct(Product.ICED_TEA, 1);
        } catch (VendingMachineException e) {
            System.out.println(e);
        }

        vm.insertCoin(Coin.CENTS_1);
        vm.insertCoin(Coin.CENTS_5);

        vm.terminate();

        vm.insertCoin(Coin.CENTS_5);
        vm.insertCoin(Coin.CENTS_10);
        vm.insertCoin(Coin.CENTS_10);
        vm.insertCoin(Coin.CENTS_25);
        vm.insertCoin(Coin.CENTS_25);
        vm.insertCoin(Coin.CENTS_50);

        try {
            vm.orderProduct(Product.WATER, 2);
        } catch (VendingMachineException e) {
            System.out.println(e);
        }
    }
}
