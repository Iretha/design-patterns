package com.smdev.structural.facade.example_1.gas_provider_2;

import com.smdev.structural.facade.example_1.Customer;

public class SuperGasPaymentProvider {

    public void payBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the gas provider 2.");
    }
}
