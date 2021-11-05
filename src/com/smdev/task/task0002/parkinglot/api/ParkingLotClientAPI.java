package com.smdev.task.task0002.parkinglot.api;

import com.smdev.task.task0002.parkinglot.domain.parking.Entrance;
import com.smdev.task.task0002.parkinglot.domain.parking.Exit;
import com.smdev.task.task0002.parkinglot.domain.parking.ParkingSpace;
import com.smdev.task.task0002.parkinglot.domain.ticket.Ticket;
import com.smdev.task.task0002.parkinglot.domain.vehicle.Vehicle;
import com.smdev.task.task0002.parkinglot.payment.PaymentStrategy;
import com.smdev.task.task0002.parkinglot.payment.Recipe;

public interface ParkingLotClientAPI {

    Ticket enter(Entrance entrance, Vehicle vehicle);

    void park(ParkingSpace space, Ticket ticket);

    Recipe pay(Ticket ticket, PaymentStrategy paymentStrategy);

    Vehicle exit(Exit exit, Ticket ticket);
}
