package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String status;
    @Column
    private int waiterId;
    @Column
    private int tableId;
    @Column
    private double price;

    public Order(int id, String status, int waiterId, int tableId, double price) {
        this.id = id;
        this.status = status;
        this.waiterId = waiterId;
        this.tableId = tableId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
