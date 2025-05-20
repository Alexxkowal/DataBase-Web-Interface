package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Compound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompoundRepository extends JpaRepository<Compound, Integer> {

    @Query(value = """
            SELECT * FROM compound c
            WHERE (:productId IS NULL OR c.product_id = :productId)
              AND (:productName IS NULL OR EXISTS (
                  SELECT 1 FROM products p WHERE p.id = c.product_id AND LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))
              ))
              AND (:dishId IS NULL OR c.dish_id = :dishId)
              AND (:dishName IS NULL OR EXISTS (
                  SELECT 1 FROM dishes d WHERE d.id = c.dish_id AND LOWER(d.dish_name) LIKE LOWER(CONCAT('%', :dishName, '%'))
              ))
              AND (:minQuantity IS NULL OR c.product_quantity >= :minQuantity)
              AND (:maxQuantity IS NULL OR c.product_quantity <= :maxQuantity)
            """,
            countQuery = """
            SELECT count(*) FROM compound c
            WHERE (:productId IS NULL OR c.product_id = :productId)
              AND (:productName IS NULL OR EXISTS (
                  SELECT 1 FROM products p WHERE p.id = c.product_id AND LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))
              ))
              AND (:dishId IS NULL OR c.dish_id = :dishId)
              AND (:dishName IS NULL OR EXISTS (
                  SELECT 1 FROM dishes d WHERE d.id = c.dish_id AND LOWER(d.dish_name) LIKE LOWER(CONCAT('%', :dishName, '%'))
              ))
              AND (:minQuantity IS NULL OR c.product_quantity >= :minQuantity)
              AND (:maxQuantity IS NULL OR c.product_quantity <= :maxQuantity)
            """,
            nativeQuery = true)
    Page<Compound> searchCompounds(
            @Param("productId") Integer productId,
            @Param("productName") String productName,
            @Param("dishId") Integer dishId,
            @Param("dishName") String dishName,
            @Param("minQuantity") Double minQuantity,
            @Param("maxQuantity") Double maxQuantity,
            Pageable pageable
    );
}
