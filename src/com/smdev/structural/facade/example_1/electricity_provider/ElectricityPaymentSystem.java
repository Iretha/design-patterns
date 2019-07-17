package com.smdev.structural.facade.example_1.electricity_provider;

import com.smdev.structural.facade.example_1.Customer;

public class ElectricityPaymentSystem {

    public void pay(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the electricity provider.");
    }
}
