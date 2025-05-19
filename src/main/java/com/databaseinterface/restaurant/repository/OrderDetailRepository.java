package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

//    @EntityGraph(attributePaths = {"order", "dish"})
    @Query(value = "SELECT od.* FROM order_details od " +
            "JOIN dishes d ON od.dish_id = d.id " +
            "WHERE (:orderId IS NULL OR od.order_id = :orderId) " +
            "AND (:dishName IS NULL OR d.dish_name ILIKE '%' || :dishName || '%')",
            countQuery = "SELECT count(*) FROM order_details od " +
                    "JOIN dishes d ON od.dish_id = d.id " +
                    "WHERE (:orderId IS NULL OR od.order_id = :orderId) " +
                    "AND (:dishName IS NULL OR d.dish_name ILIKE '%' || :dishName || '%')",
            nativeQuery = true)
    Page<OrderDetail> findByOrderIdAndDishId(
            @Param("orderId") Integer orderId,
            @Param("dishName") String dishName,
            Pageable pageable);
}

