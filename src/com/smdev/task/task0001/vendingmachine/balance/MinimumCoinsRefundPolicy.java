package com.smdev.task.task0001.vendingmachine.balance;

public class MinimumCoinsRefundPolicy extends RefundPolicy {
    @Override
    protected CoinCollection calculateCoinsToRefund(CoinCollection collection, int refundAmount) {
        return new CoinCollection();
    }
}
