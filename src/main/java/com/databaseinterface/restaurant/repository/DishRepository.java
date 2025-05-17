package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    // Здесь можно добавить свои методы, если нужно
}
