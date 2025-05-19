package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products p " +
            "WHERE (:name IS NULL OR p.name ILIKE '%' || :name || '%') " +
            "AND (:type IS NULL OR p.type ILIKE '%' || :type || '%')",
            countQuery = "SELECT count(*) FROM products p " +
                    "WHERE (:name IS NULL OR p.name ILIKE '%' || :name || '%') " +
                    "AND (:type IS NULL OR p.type ILIKE '%' || :type || '%')",
            nativeQuery = true)
    Page<Product> searchProducts(@Param("name") String name,
                                 @Param("type") String type,
                                 Pageable pageable);
}
