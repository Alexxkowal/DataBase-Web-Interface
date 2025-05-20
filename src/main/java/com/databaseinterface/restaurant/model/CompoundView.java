package com.databaseinterface.restaurant.model;


import jakarta.persistence.*;

@Entity
@Table(name = "compounds")
@IdClass(CompoundViewId.class) // составной ID-класс
public class CompoundView {

    @Id
    @Column(name = "dish_name")
    private String dishName;

    @Id
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private String productQuantity;

    // Геттеры и сеттеры
    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductQuantity() { return productQuantity; }
    public void setProductQuantity(String productQuantity) { this.productQuantity = productQuantity; }
}
