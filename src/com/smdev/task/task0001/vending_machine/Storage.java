package com.smdev.task.task0001.vending_machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Storage {

    private Map<ProductType, List<StorageSlot>> storage = new HashMap<>();

    public Storage(List<StorageSlot> slots) {
        if (slots != null) {
            this.storage = slots.stream().collect(Collectors.groupingBy(StorageSlot::getProductType));
        }
    }

    public boolean isProductAvailable(ProductType product) {
        StorageSlot slot = findSlotWithProduct(product);
        return slot != null;
    }

    public void releaseProduct(ProductType product) throws Exception {
        StorageSlot slot = findSlotWithProduct(product);
        if (slot == null) {
            throw new Exception("Product not available");
        }
        slot.releaseProduct();
    }

    private StorageSlot findSlotWithProduct(ProductType product) {
        List<StorageSlot> slots = this.storage.get(product);
        for (StorageSlot slot : slots) {
            if (slot.hasProducts()) {
                return slot;
            }
        }
        return null;
    }
}
