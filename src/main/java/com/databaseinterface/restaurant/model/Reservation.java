package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Reservation() {
    }

    @Column
    private int tableId;
    @Column
    private int clientId;
    @Column
    private LocalDate reservationDate;
    @Column
    private LocalTime reservationTime;

    public Reservation(int id, int tableId, int clientId, LocalDate reservationDate, LocalTime reservationTime) {
        this.id = id;
        this.tableId = tableId;
        this.clientId = clientId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
