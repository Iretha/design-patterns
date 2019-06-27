package com.smdev.gof.structural.facade.example_1;

public class _Main {

    public static void main(String[] args) {
        // Jon Snow
        PaymentServicesFacade paymentServices1 = new PaymentServicesFacadeImpl(new Customer("Jon Snow"));
        paymentServices1.payElectricityBill("BN123/20");
        paymentServices1.payGasBillToProvider2("233834esd4rcWQWFG3");
        paymentServices1.payPhoneBill("122398");

        System.out.println("");

        // Alice Young
        PaymentServicesFacade paymentServices2 = new PaymentServicesFacadeImpl(new Customer("Alice Young"));
        paymentServices2.payGasBillToProvider1("ACVVVS");
        paymentServices2.payGasBillToProvider1("YYRSG2sh");
    }
}
