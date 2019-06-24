package com.smdev.gof.creational.builder.example_2_lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(builderMethodName = "hiddenBuilder")
public class Car {

    private String vin; // vehicle identification number is a required  field
    private String brand; // optional
    private String model; // optional
    private Integer modelYear; // optional

    /**
     * Necessary only if you have some required fields
     * @param vin
     * @return
     */
    public static Car.CarBuilder builder(String vin) {
        return hiddenBuilder().vin(vin);
    }
}
