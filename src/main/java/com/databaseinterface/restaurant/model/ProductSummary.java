package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_summary")
public class ProductSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int totalProducts;

    public ProductSummary(int id, int totalProducts) {
        this.id = id;
        this.totalProducts = totalProducts;
    }

    public ProductSummary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

}
