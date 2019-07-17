package com.smdev.behavioral.state.example_1;

public class _Main {

    public static void main(String[] args) {
        OrderContext ctx = new OrderContext("Jon Snow", "123 Str, 23 doorbell"); // order received
        ctx.next(); // -> for assembly

        ctx.next(); // -> assembly in progress
        ctx.next(); // -> for baking
        ctx.next(); // -> baking in progress
        ctx.next(); // -> for delivery
        ctx.next(); // -> delivery in progress
        ctx.next(); // -> delivered to customer

        ctx.next(); // -> order completed
    }
}
