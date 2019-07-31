package com.smdev.task.task0001.vending_machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductStorage {

    private Map<Product, List<ProductSlot>> storage = new HashMap<>();

    public ProductStorage(List<ProductSlot> slots) {
        if (slots != null) {
            this.storage = slots.stream().collect(Collectors.groupingBy(ProductSlot::getProductType));
        }
    }

    public boolean isProductAvailable(Product product) {
        ProductSlot slot = findSlotWithProduct(product);
        return slot != null;
    }

    public void releaseProduct(Product product) throws Exception {
        ProductSlot slot = findSlotWithProduct(product);
        if (slot == null) {
            throw new Exception("Product not available");
        }
        slot.releaseProduct();
    }

    private ProductSlot findSlotWithProduct(Product product) {
        List<ProductSlot> slots = this.storage.get(product);
        for (ProductSlot slot : slots) {
            if (slot.hasProducts()) {
                return slot;
            }
        }
        return null;
    }
}
