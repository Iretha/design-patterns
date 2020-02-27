package com.smdev.task.task0001.vendingmachine;

import com.smdev.task.task0001.vendingmachine.balance.MinimumCoinsRefundPolicy;
import com.smdev.task.task0001.vendingmachine.security.SecurityPolicy;

public class VendingMachineFactory {

    public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl(new SecurityPolicy(), new MinimumCoinsRefundPolicy());
    }
}
