package com.smdev.structural.facade.example_1;

public interface PaymentServicesFacade {

    void payPhoneBill(String billNo);

    void payElectricityBill(String billNo);

    void payGasBillToProvider1(String billNo);

    void payGasBillToProvider2(String billNo);
}
