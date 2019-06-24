package com.smdev.gof.creational.builder.example_1;

public class Main {

    public static void main(String[] args) {
        Car car1 = Car.builder("EXAMPLE_VIN1").brand("FORD").model("KA").modelYear(2007).build();
        System.out.println(car1.toString());

        Car car2 = Car.builder("EXAMPLE_VIN2").modelYear(2007).brand("FORD").model("KA").build();
        System.out.println(car2.toString());

        Car car3 = Car.builder("EXAMPLE_VIN3").brand("FORD").build();
        System.out.println(car3.toString());
    }
}
