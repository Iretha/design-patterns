package com.smdev.task.task0001.vendingmachine;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Aggregate object
 * @param <I>
 */
public abstract class ItemCollection<I extends Item> {

    @Getter
    private Map<I, Integer> items;

    public ItemCollection() {
        this.items = new TreeMap<>();
    }

    public void add(I item, int quantity) {
        this.items.put(item, getQuantity(item) + quantity);
    }

    public boolean release(I item, int quantity) {
        if (hasQuantity(item, quantity)) {
            this.items.put(item, getQuantity(item) - quantity);
            return true;
        }
        return false;
    }

    public boolean hasQuantity(I item, int quantity) {
        return getQuantity(item) >= quantity;
    }

    public Integer getQuantity(I item) {
        return this.items.getOrDefault(item, 0);
    }

    public void clearQuantities() {
        this.items.forEach((k, v) -> release(k, v));
    }
}
