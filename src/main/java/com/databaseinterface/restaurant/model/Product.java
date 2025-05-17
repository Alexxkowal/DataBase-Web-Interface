package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private double pricePerUnit;
    @Column
    private int calorieContent;

    public Product(int id, String name, String type, double pricePerUnit, int calorieContent) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerUnit = pricePerUnit;
        this.calorieContent = calorieContent;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getCalorieContent() {
        return calorieContent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setCalorieContent(int calorieContent) {
        this.calorieContent = calorieContent;
    }
}
