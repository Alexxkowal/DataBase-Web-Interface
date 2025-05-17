package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.ProductSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSummaryRepository extends JpaRepository<ProductSummary, Integer> {
}
