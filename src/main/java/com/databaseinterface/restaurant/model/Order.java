package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String status = "Принят";

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Staff waiter;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModel table;

    @Column
    private double price;

    public Order() {
    }

    public Order(int id, String status, Staff waiter, TableModel table, double price) {
        this.id = id;
        this.status = status;
        this.waiter = waiter;
        this.table = table;
        this.price = price;
    }

    // геттеры и сеттеры

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

    public Staff getWaiter() {
        return waiter;
    }

    public void setWaiter(Staff waiter) {
        this.waiter = waiter;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
