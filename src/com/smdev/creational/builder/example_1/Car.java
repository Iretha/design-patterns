package com.smdev.creational.builder.example_1;

public class Car {
    private String vin; // vehicle identification number is a required  field
    private String brand; // optional
    private String model; // optional
    private Integer modelYear; // optional

    private Car(String vin, String brand, String model, Integer modelYear) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
    }

    public static CarBuilder builder(String vin) {
        return new CarBuilder(vin);
    }

    static class CarBuilder {
        private String vin;
        private String brand;
        private String model;
        private Integer modelYear;

        private CarBuilder(String vin) {
            this.vin = vin;
        }

        public Car build() {
            return new Car(this.vin, this.brand, this.model, this.modelYear);
        }

        public CarBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder modelYear(Integer modelYear) {
            this.modelYear = modelYear;
            return this;
        }
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder().append("Car(")
                .append("vin=").append(this.vin).append(", ")
                .append("brand=").append(this.brand).append(", ")
                .append("model=").append(this.model).append(", ")
                .append("model year=").append(this.modelYear).append(")");
        return b.toString();
    }
}
