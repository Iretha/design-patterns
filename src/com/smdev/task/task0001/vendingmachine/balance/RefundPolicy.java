package com.smdev.task.task0001.vendingmachine.balance;

/**
 * RefundStrategy uses Template Method to build an algorithm for calculating the change in coins
 */
public abstract class RefundPolicy {

    public CoinCollection calculateRefund(int refundAmount, CoinCollection... collections) {
        CoinCollection joinedCollection = joinCollections(collections);
        return calculateCoinsToRefund(joinedCollection, refundAmount);
    }

    protected abstract CoinCollection calculateCoinsToRefund(CoinCollection collection, int refundAmount);

    private CoinCollection joinCollections(CoinCollection[] collections) {
        CoinCollection joinedCollection = new CoinCollection();
        if (collections == null) {
            return joinedCollection;
        }
        for (CoinCollection collection : collections) {
            if (collection == null || collection.isEmpty()) {
                continue;
            }
            collection.getItems().forEach((k, v) -> joinedCollection.add(k, v));
        }
        return joinedCollection;
    }


}
