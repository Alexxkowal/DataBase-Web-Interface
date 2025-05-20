package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query(value = "SELECT * FROM dishes d " +
            "WHERE (:name IS NULL OR d.dish_name ILIKE '%' || :name || '%') " +
            "AND (:minPrice IS NULL OR d.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR d.price <= :maxPrice) " +
            "AND (:category IS NULL OR d.dish_category = :category)",
            countQuery = "SELECT count(*) FROM dishes d " +
            "WHERE (:name IS NULL OR d.dish_name ILIKE '%' || :name || '%') " +
            "AND (:minPrice IS NULL OR d.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR d.price <= :maxPrice) " +
            "AND (:category IS NULL OR d.dish_category = :category)",
            nativeQuery = true)
    Page<Dish> searchDishes(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("category") Integer category,
            Pageable pageable);

}
