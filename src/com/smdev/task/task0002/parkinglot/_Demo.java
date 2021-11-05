package com.smdev.task.task0002.parkinglot;

import com.smdev.task.task0002.parkinglot.domain.display.Display;
import com.smdev.task.task0002.parkinglot.domain.parking.*;

import java.util.List;

public class _Demo {

    public static void main(String[] args) {
        ParkingLot parking = new ParkingLot();

        ParkingFloor floor1 = new ParkingFloor(parking, 1);
        Entrance entrance11 = new Entrance(1, floor1);
        Entrance entrance12 = new Entrance(2, floor1);
        Exit exit11 = new Exit(1, floor1);
        Exit exit12 = new Exit(2, floor1);
        ParkingSpace parkingSpace11 = new ParkingSpace(1, floor1, ParkingSpaceSize.XXS, List.of(ParkingSpaceFeature.ELECTRIC_PANEL));
        ParkingSpace parkingSpace12 = new ParkingSpace(2, floor1, ParkingSpaceSize.M, List.of(ParkingSpaceFeature.ELECTRIC_PANEL));
        ParkingSpace parkingSpace13 = new ParkingSpace(3, floor1, ParkingSpaceSize.M, null);
        ParkingSpace parkingSpace14 = new ParkingSpace(4, floor1, ParkingSpaceSize.XL, null);



        ParkingFloor floor2 = new ParkingFloor(parking, 2);
        Entrance entrance21 = new Entrance(1, floor2);
        Exit exit21 = new Exit(1, floor2);
        ParkingSpace parkingSpace21 = new ParkingSpace(1, floor2, ParkingSpaceSize.M, List.of(ParkingSpaceFeature.ELECTRIC_PANEL));
        ParkingSpace parkingSpace22 = new ParkingSpace(2, floor2, ParkingSpaceSize.L);
        ParkingSpace parkingSpace23 = new ParkingSpace(3, floor2, ParkingSpaceSize.XL);





        new Display().displayInfo(parking);

    }
}
