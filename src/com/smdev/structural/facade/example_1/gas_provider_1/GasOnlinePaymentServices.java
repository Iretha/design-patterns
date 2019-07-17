package com.smdev.structural.facade.example_1.gas_provider_1;

import com.smdev.structural.facade.example_1.Customer;

public class GasOnlinePaymentServices {

    public void payBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the gas provider 1.");
    }
}
