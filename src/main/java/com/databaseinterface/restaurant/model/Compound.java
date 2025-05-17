package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compound")
public class Compound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int prodcutId;
    @Column
    private int dishId;
    @Column
    private double productQuantity;

    public Compound() {
    }

    public Compound(int id, int prodcutId, int dishId, double productQuantity) {
        this.id = id;
        this.prodcutId = prodcutId;
        this.dishId = dishId;
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(int prodcutId) {
        this.prodcutId = prodcutId;
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
