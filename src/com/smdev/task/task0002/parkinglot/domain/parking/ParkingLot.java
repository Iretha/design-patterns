package com.smdev.task.task0002.parkinglot.domain.parking;

import com.smdev.task.task0002.parkinglot.api.ParkingLotClientAPI;
import com.smdev.task.task0002.parkinglot.domain.display.Displayable;
import com.smdev.task.task0002.parkinglot.domain.ticket.Ticket;
import com.smdev.task.task0002.parkinglot.domain.vehicle.Vehicle;
import com.smdev.task.task0002.parkinglot.payment.PaymentStrategy;
import com.smdev.task.task0002.parkinglot.payment.Recipe;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Displayable, ParkingLotClientAPI {

    @Getter
    private List<ParkingFloor> floors;

    @Getter
    private int totalSpacesCapacity = 0;

    @Getter
    private int availableSpaces = 0;

    public ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
        this.totalSpacesCapacity += floor.getCapacity();
        this.availableSpaces += floor.getAvailable();
    }

    public synchronized void updateInfo() {

        int tmpTotal = 0;
        int tmpAvailable = 0;
        for (ParkingFloor floor : getFloors()) {
            tmpTotal += floor.getCapacity();
            tmpAvailable += floor.getAvailable();
        }

        this.totalSpacesCapacity = tmpTotal;
        this.availableSpaces = tmpAvailable;
    }

    @Override
    public String displayInfo() {
        StringBuilder str = new StringBuilder();
        str.append("ParkingLot info: ").append("\n")
                .append("Total Parking Capacity: ").append(getTotalSpacesCapacity()).append("\n")
                .append("Available: ").append(getAvailableSpaces()).append("\n")
                .append("Floors: ").append(getFloors().size()).append("\n");

        getFloors().forEach(f -> str.append("\t").append(f.displayInfo()));
        return str.toString();
    }



    @Override
    public Ticket enter(Entrance entrance, Vehicle vehicle) {
        return null;
    }

    @Override
    public void park(ParkingSpace space, Ticket ticket) {

    }

    @Override
    public Recipe pay(Ticket ticket, PaymentStrategy paymentStrategy) {
        return null;
    }

    @Override
    public Vehicle exit(Exit exit, Ticket ticket) {
        return null;
    }
}
