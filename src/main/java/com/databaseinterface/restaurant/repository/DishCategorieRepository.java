package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.DishCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishCategorieRepository extends JpaRepository<DishCategorie, Integer> {
}
