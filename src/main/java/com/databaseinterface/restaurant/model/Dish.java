package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String dishName;
    @Column
    private double price;
    @Column
    private int dishCategory;

    public Dish() {
    }

    public Dish(int id, String dishName, double price, int dishCategory) {
        this.id = id;
        this.dishName = dishName;
        this.price = price;
        this.dishCategory = dishCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(int dishCategory) {
        this.dishCategory = dishCategory;
    }
}
