package com.smdev.gof.structural.facade.example_1.telecomm;

import com.smdev.gof.structural.facade.example_1.Customer;

public class TelecommOnlinePayDesk {

    public void payPhoneBill(Customer customer, String billNo) {
        System.out.println("Payment for billNo= " + billNo + " from " + customer.getName() + " received by the mobile services provider.");
    }
}
