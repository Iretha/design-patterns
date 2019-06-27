package com.smdev.gof.structural.facade.example_1;

import com.smdev.gof.structural.facade.example_1.electricity_provider.ElectricityPaymentSystem;
import com.smdev.gof.structural.facade.example_1.gas_provider_1.GasOnlinePaymentServices;
import com.smdev.gof.structural.facade.example_1.gas_provider_2.SuperGasPaymentProvider;
import com.smdev.gof.structural.facade.example_1.telecomm.TelecommOnlinePayDesk;
import lombok.Getter;

public class PaymentServicesFacadeImpl implements PaymentServicesFacade {

    @Getter
    private Customer customer;

    public PaymentServicesFacadeImpl(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void payPhoneBill(String bill) {
        TelecommOnlinePayDesk onlinePayDesk = new TelecommOnlinePayDesk();
        onlinePayDesk.payPhoneBill(getCustomer(), bill);
    }

    @Override
    public void payElectricityBill(String bill) {
        ElectricityPaymentSystem paymentSystem = new ElectricityPaymentSystem();
        paymentSystem.pay(getCustomer(), bill);
    }

    @Override
    public void payGasBillToProvider1(String bill) {
        GasOnlinePaymentServices paymentServices = new GasOnlinePaymentServices();
        paymentServices.payBill(getCustomer(), bill);
    }

    @Override
    public void payGasBillToProvider2(String bill) {
        SuperGasPaymentProvider paymentServices = new SuperGasPaymentProvider();
        paymentServices.payBill(getCustomer(), bill);
    }
}
