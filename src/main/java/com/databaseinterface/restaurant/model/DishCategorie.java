package com.databaseinterface.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dish_categories")
public class DishCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String categoryName;

    public DishCategorie(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public DishCategorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
