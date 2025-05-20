package com.databaseinterface.restaurant.model;


import java.io.Serializable;
import java.util.Objects;

public class CompoundViewId implements Serializable {

    private String dishName;
    private String productName;

    // Геттеры, сеттеры, equals и hashCode
    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompoundViewId)) return false;
        CompoundViewId that = (CompoundViewId) o;
        return Objects.equals(dishName, that.dishName) &&
               Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishName, productName);
    }
}
