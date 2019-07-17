package com.smdev.structural.decorator.example_1.decor.delivery;

import com.smdev.structural.decorator.example_1.Product;
import com.smdev.structural.decorator.example_1.decor.AbstractDecorator;

public class DeliveryDecorator extends AbstractDecorator {

    public DeliveryDecorator(Product base) {
        super(base);
    }

    @Override
    public double getPrice() {
        double price = 0;
        double basePrice = super.getPrice();
        if(basePrice > 10){
            price = basePrice; // free delivery for orders over 10 bucks
        }else{
            price = 5.8 + basePrice;
        }
        return price;
    }

}
