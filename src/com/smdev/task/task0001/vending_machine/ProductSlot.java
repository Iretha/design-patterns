package com.smdev.task.task0001.vending_machine;

import lombok.Getter;

public class ProductSlot {

    @Getter
    private Product productType;

    @Getter
    private int quantity;

    public ProductSlot(Product productType, int initialQuantity) {
        this.productType = productType;
        this.quantity = initialQuantity;
    }

    public boolean hasProducts() {
        return this.quantity > 0;
    }

    public void releaseProduct() throws Exception {
        if (!hasProducts()) {
            throw new Exception("Product not available");
        }

        this.quantity--;
        System.out.println("Product " + getProductType().getLabel() + " released. Thank you for your purchase!");
    }
}
