package com.smdev.task.task0002.parkinglot.domain.ticket;

import com.smdev.task.task0002.parkinglot.domain.vehicle.Vehicle;
import com.smdev.task.task0002.parkinglot.domain.parking.Entrance;
import com.smdev.task.task0002.parkinglot.domain.parking.Exit;

import java.time.LocalDateTime;

public class Ticket {

    private int no;

    private Entrance issuedFrom;

    private LocalDateTime issuedOn;

    private Vehicle issuedTo;

    private LocalDateTime freeUntil;

    private Exit leftFrom;

    private LocalDateTime leftOn;


}
