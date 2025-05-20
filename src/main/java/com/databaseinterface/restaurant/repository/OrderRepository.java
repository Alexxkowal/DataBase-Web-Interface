package com.databaseinterface.restaurant.repository;


import com.databaseinterface.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = """
    SELECT * FROM orders o
    WHERE (:orderId IS NULL OR o.id = :orderId)
      AND (:status IS NULL OR o.status ILIKE '%' || :status || '%')
      AND (:minPrice IS NULL OR o.price >= :minPrice)
      AND (:maxPrice IS NULL OR o.price <= :maxPrice)
    """,
    countQuery = """
    SELECT count(*) FROM orders o
    WHERE (:orderId IS NULL OR o.id = :orderId)
      AND (:status IS NULL OR o.status ILIKE '%' || :status || '%')
      AND (:minPrice IS NULL OR o.price >= :minPrice)
      AND (:maxPrice IS NULL OR o.price <= :maxPrice)
    """,
    nativeQuery = true)
Page<Order> searchOrders(@Param("orderId") Integer orderId,
                         @Param("status") String status,
                         @Param("minPrice") Double minPrice,
                         @Param("maxPrice") Double maxPrice,
                         Pageable pageable);

}
