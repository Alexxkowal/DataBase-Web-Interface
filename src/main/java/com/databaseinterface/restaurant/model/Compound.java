package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compound")
public class Compound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int productId;
    @Column
    private int dishId;
    @Column
    private double productQuantity;
    @Transient
    private String productName;

    @Transient
    private String dishName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Compound() {
    }

    public Compound(int id, int productId, int dishId, double productQuantity, String productName, String dishName) {
        this.id = id;
        this.productId = productId;
        this.dishId = dishId;
        this.productQuantity = productQuantity;
        this.productName = productName;
        this.dishName = dishName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int prodcutId) {
        this.productId = prodcutId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(double productQuantity) {
        this.productQuantity = productQuantity;
    }
}
