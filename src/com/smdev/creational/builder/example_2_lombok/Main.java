package com.smdev.creational.builder.example_2_lombok;


public class Main {

    public static void main(String[] args) {
        Car car1 = Car.builder("EXAMPLE_VIN11").brand("FORD").model("KA").modelYear(2007).build();
        System.out.println(car1.toString());

        Car car2 = Car.builder("EXAMPLE_VIN22").modelYear(2007).brand("FORD").model("KA").build();
        System.out.println(car2.toString());

        Car car3 = Car.builder("EXAMPLE_VIN33").brand("FORD").build();
        System.out.println(car3.toString());
    }
}
