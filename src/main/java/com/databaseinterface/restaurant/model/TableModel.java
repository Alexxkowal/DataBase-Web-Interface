package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int capacity;
    @Column
    private String zone;

    public TableModel(int id, int capacity, String zone) {
        this.id = id;
        this.capacity = capacity;
        this.zone = zone;
    }

    public TableModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
