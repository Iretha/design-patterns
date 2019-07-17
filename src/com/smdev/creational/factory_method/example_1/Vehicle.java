package com.smdev.creational.factory_method.example_1;

public abstract class Vehicle {

   public void drive() {
      VehicleDriver driver = createDriverInstance();
      driver.driveVehicle(this);
   }

   protected abstract VehicleDriver createDriverInstance();
}
